package honeyalarmg2

import org.springframework.security.access.annotation.Secured
import honeyalarm.Helpers;

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
class HoneypotController {

    def springSecurityService


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

    //        if (honeypot.password.toString() == password)
    //        {
    //            return render("bad username / password combination")
    //        }

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

        def username = ""
        def token = ""
        def source

        def contract = new XmlSlurper().parseText(request.reader.text)

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

            def honeypot = Honeypot.findByNameAndPassword(username, token)

            //
            // generate update entry for ui
            //
            Report newReport = new Report(type: alertType, time: new Date(), request: "" + requestURL, status: "OPEN", attacker: "" + it.Source)
            //request: "request", status: "OPEN", changedFromIP: source)
            newReport.save(flush: true)

            UIReport newHoneypotUpdate = new UIReport(type: "ALARM", time: "" + it.CreateTime, text: "Alarm call from honeypot " + it.Analyzer.@'id')
            updateUIReport(newHoneypotUpdate)

            ipUpdate(source)


        }


        return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>OK</StatusCode><Text></Text></Result>")

    }

    def renderPlainText(returnText)
    {
        return render(text: returnText, contentType: "text/plain", encoding: "UTF-8")
    }

    
    def index()
    {
        def Honeypots = Honeypot.findAll()
        def dateAdded = new Date()

        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]


        [role: role, Honeypots: Honeypots, dateAdded: dateAdded]
    }

}
