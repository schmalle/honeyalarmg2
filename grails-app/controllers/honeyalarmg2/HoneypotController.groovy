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

        def name = request.XML?.login?.name
        def password = request.XML?.login?.password

        def honeypot = Honeypot.findByName(name)

        if (honeypot.password.toString() == password)
        {
            return render("bad username / password combination")
        }

        honeypot.properties['ip'] = params
        honeypot.properties['lastseen'] = new Date()
        honeypot.save()

        //
        // generate update entry for ui
        //
        UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Keep alive call from honeypot " + name)
        newHoneypotUpdate.save()

        return render("ok")

    }   // updateIP


    def report()
    {

        def username = request.XML?.Authentication?.username
        def token = request.XML?.Authentication?.token
        def time = request.XML?.Alert?.CreateTime
        def source = request.XML?.Alert?.Source
        def target = request.XML?.Alert?.Target


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

        return render("ok")

    }

}
