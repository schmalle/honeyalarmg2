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


    def report()
    {

        def username = ""
        def token = ""
        def time = ""
        def source = ""
        def target = ""

        try
        {
            username = request.XML?.Authentication?.username
            token = request.XML?.Authentication?.token
            time = request.XML?.Alert?.CreateTime
            source = request.XML?.Alert?.Source
            target = request.XML?.Alert?.Target
        }
        catch (Exception e)
        {
            return render("report:: not ok")
        }


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


        def honeypot = Honeypot.findByNameAndPassword(username, token)

        //
        // generate update entry for ui
        //
        Report newReport = new Report(type: "DEMO", time: new Date(), request: "Dummy", status: "OPEN", attacker: "" + source) //request: "request", status: "OPEN", changedFromIP: source)
        newReport.save(flush: true)

        UIReport newHoneypotUpdate = new UIReport(type: "ALARM", time: "" + time, text: "Alarm call from honeypot " + username)
        newHoneypotUpdate.save(flush: true)

        return renderPlainText("ok")

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
