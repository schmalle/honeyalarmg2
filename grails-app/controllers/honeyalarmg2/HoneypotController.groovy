package honeyalarmg2

class HoneypotController {

    //
    // for the moment only scaffoling for index action
    //
    def scaffold = true;


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

            return renderText("ok")
        }
        catch (Exception e)
        {
            return renderText("not ok, exception caught")
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

        return renderText("ok")

    }

    def renderText(returnText)
    {
        return render(text: returnText, contentType: "text/plain", encoding: "UTF-8")
    }

}
