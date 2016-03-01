package honeyalarmg2

import org.springframework.security.access.annotation.Secured
import honeyalarm.Helpers;
import org.codehaus.groovy.grails.validation.routines.InetAddressValidator


@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
class HoneypotController {

    transient springSecurityService
    transient twitterService
    transient eWSService

    @Secured(["ROLE_ADMIN"])
    def delete() {

        def honeypot = Honeypot.findById(params.id)
        if (honeypot != null)
        {
            Console.println("Info: Honeypot already existing")
            honeypot.delete(flush: true)
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot " + params.id + " deleted"])
        }
        else
        {
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot " + params.id + " not found"])
        }
    }


    //
    // update UI
    //
    def updateUIReport(report)
    {
        def reports = UIReport.findAll()
        if (reports.size() < 8)
            report.save()
        else {

            UIReport rep0 = reports.get(0)
            rep0.delete(flush: true)
            report.save(flush: true)

        }

    }

    //
    // saves a new honeypot instance if not already existing
    //
    def saveHoneypot() {

        String name = params.name

        def honeypot = Honeypot.findByName(name)
        if (honeypot != null)
        {
            Console.println("Info: Honeypot already existing")
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot name already existing"])
        }
        else
        {
            honeypot = new Honeypot(params)

            // fill necessary dummy data
            honeypot.ip = "new"
            honeypot.lastseen = "new"
            honeypot.save(flush: true)
            redirect  (controller: "Honeypot" , action:"index")
        }
    }

    /*
        handle the periodical update request from the honeypots
     */
    def updateIP()
    {

        try
        {

            def name = request.XML?.login?.name
            def password = request.XML?.login?.password

            def honeypot = Honeypot.findByName(name)
            if (!honeypot)
            {
                UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Keep alive call from unknown honeypot " + name)
                updateUIReport(newHoneypotUpdate)

                return renderPlainText("not ok")
            }

            honeypot.delete()
            honeypot.properties['ip'] = request.remoteAddr
            honeypot.properties['lastseen'] = new Date()
            honeypot.save()


            //
            // generate update entry for ui
            //
            UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Keep alive call from honeypot " + name)
            updateUIReport(newHoneypotUpdate)

            return renderPlainText("ok")
        }
        catch (Exception e)
        {
            return renderPlainText("not ok, exception caught")
        }
        
    }   // updateIP



    /*
        Update attacking IPs
     */
    def ipUpdate(source)
    {
        def ip



        def reports = IP.findAll("from IP as b where b.text='" + source + "'")
        if (reports.size() == 0)
            ip = new IP(text: "" + source, firstSeen: new Date(), lastSeen: new Date())
        else
            ip = reports.get(0)

        ip.lastSeen = new Date()

        ip.save(flush: true)


    }

    def report()
    {

        java.lang.String source
        def contract = new XmlSlurper().parseText(request.reader.text)
        def username = contract.Authentication.username
        java.lang.String token = contract.Authentication.token

        User user = User.findByUsername(username)
        String tokenFromDatabase = user.pwbackup
        token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(token);

        if (!user)
            return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")


        def match = token == tokenFromDatabase

       if (!match)
           return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")

        contract.Alert.each() {
            source = it.Source

            def requestURL = "<UNDEFINED>"
            def alertType = "BINARY"


            it.Request.each() {

                def type = it.@'type'.text()
                def url = 'url'

                println "   Request Type: " + type + " Content: " + it

                def test = type == url

                if (test)
                {
                    requestURL = it
                    print "Found URL Type : " + type
                    alertType = "WEB"
                }

            }


            String analyzerID = it.Analyzer.@'id'

            def analyzer = Honeypot.findByName(analyzerID)
            if (!analyzer)
            {
                UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "First call from honeypot " + analyzerID + "(creating honeypot)")
                updateUIReport(newHoneypotUpdate)

                analyzer = new Honeypot(ip: request.remoteAddr, added: new Date(), lastseen: new Date(), name: analyzerID)
                analyzer.save(flush: true)

            }
            else {
                analyzer.delete()
                analyzer.properties['ip'] = request.remoteAddr
                analyzer.properties['lastseen'] = new Date()
                analyzer.save()
            }


            //
            // only show reports, which are not already in an ignored list
            //
            if (InetAddressValidator.getInstance().isValid(source) && notIgnored(requestURL))
            {

                ConfigHG config = ConfigHG.findAll().get(0)
                List<User> userList = User.findAll()

                if (config.useTwitter == "yes")
                    twitterService.directMessageList(userList, "New alarm from Hp reporting UI")

                if (config.useSicherheitstacho == "yes")
                    eWSService.sendAlarm(new Date(), it.Source, requestURL, analyzerID)



                //
                // generate update entry for ui
                //
                Report newReport = new Report(type: alertType, time: new Date(), request: "" + requestURL, status: "OPEN", attacker: "" + it.Source)
                newReport.save(flush: true)

                UIReport newHoneypotUpdate = new UIReport(type: "ALARM", time: "" + new Date(), text: "Alarm call from honeypot " + analyzerID)
                updateUIReport(newHoneypotUpdate)

                ipUpdate(source)


            }

        }


        return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>OK</StatusCode><Text></Text></Result>")

    }


    boolean notIgnored(requestURL) {

        java.lang.String conv = requestURL
        def checksum = org.apache.commons.codec.digest.DigestUtils.sha256Hex(conv)

        def ignore = IgnoredRequests.findByChecksum(checksum)

        return (ignore == null)
    }


    //
    // render plain text
    //
    def renderPlainText(returnText)
    {
        return render(text: returnText, contentType: "text/plain", encoding: "UTF-8")
    }


    //
    // show index page
    //
    def index()
    {
        def Honeypots = Honeypot.findAll()
        def dateAdded = new Date()

        //
        // retrieve current role and pass it to UI
        //
        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]


        [role: role, Honeypots: Honeypots, dateAdded: dateAdded]
    }

}
