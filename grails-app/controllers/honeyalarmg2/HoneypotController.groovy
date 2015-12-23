package honeyalarmg2

import org.springframework.security.access.annotation.Secured

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
                newHoneypotUpdate.save()
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
            newHoneypotUpdate.save()

            return renderPlainText("ok")
        }
        catch (Exception e)
        {
            return renderPlainText("not ok, exception caught")
        }
        
    }   // updateIP



    def ipUpdate(source) {

        try
        {
            def ip = IP.findbyText(source)
            ip.lastSeen = new Date()
            ip.save(flush: true)
        }
        catch (Exception e)
        {
            def ip = new IP(text: "" + source, firstSeen: new Date(), lastSeen: new Date())
            ip.save(flush: true)
        }
    }

    def report()
    {

        def username = ""
        def token = ""
        def source = ""


            def contract = new XmlSlurper().parseText(request.reader.text)

            contract.Alert.each(){
                println "ID: "  + it.Analyzer.@'id'
                println "Time:" + it.CreateTime
                println "Source:" + it.Source
                println "Target:" + it.Target

                ipUpdate(source)


                it.Request.each() {
                    println "   Request Type: " + it.@'type' + " Content: " + it
                }

/*


                    <Alert>
        <Analyzer id="4711"/>
        <CreateTime tz="+0200">2015-09-09 16:39:21</CreateTime>
        <Source category="ipv4" port="" protocol="tcp">1111</Source>
        <Target category="ipv4" port="80" protocol="tcp">1.2.3.4</Target>
        <Request type="url">/cgi-bin/.br/style.css</Request>
        <Request type="raw">R0VUIC9jZ2ktYmluLy5ici9zdHlsZS5jc3MgSFRUUC8xLjENCkFjY2VwdDogdGV4dC9jc3MsKi8q
            O3E9MC4xLCovKg0KQWNjZXB0LUVuY29kaW5nOiBnemlwLGRlZmxhdGUNCkNvbm5lY3Rpb246IEtl
            ZXAtYWxpdmUNCkZyb206IGdvb2dsZWJvdChhdClnb29nbGVib3QuY29tDQpIb3N0OiB3d3cud2Vi
            bWFpbGhvdXNlLmRlDQpSZWZlcmVyOiBodHRwOi8vd3d3LndlYm1haWxob3VzZS5kZS9jZ2ktYmlu
            Ly5ici9wYXRoLnBocA0KVXNlci1BZ2VudDogTW96aWxsYS81LjAgKGNvbXBhdGlibGU7IEdvb2ds
            ZWJvdC8yLjE7ICtodHRwOi8vd3d3Lmdvb2dsZS5jb20vYm90Lmh0bWwp
        </Request>
        <Request type="description">WebHoneypot : Glastopf v3.1</Request>
        <AdditionalData meaning="host" type="string">www.webe.de</AdditionalData>
        <AdditionalData meaning="sqliteid" type="integer">3688</AdditionalData>
    </Alert>

                 */



                def honeypot = Honeypot.findByNameAndPassword(username, token)

                //
                // generate update entry for ui
                //
                Report newReport = new Report(type: "DEMO", time: new Date(), request: "Dummy", status: "OPEN", attacker: "" + source) //request: "request", status: "OPEN", changedFromIP: source)
                newReport.save(flush: true)

                UIReport newHoneypotUpdate = new UIReport(type: "ALARM", time: "" + it.CreateTime, text: "Alarm call from honeypot " + it.Analyzer.@'id')
                newHoneypotUpdate.save(flush: true)


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
