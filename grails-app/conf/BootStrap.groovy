import honeyalarmg2.Report
import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport
import honeyalarmg2.User
import honeyalarmg2.ConfigHG

class BootStrap {

    def init = { servletContext ->


        //
        // init some dummy variables
        //

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        Honeypot honey = new Honeypot(name: "demohoneypot", password: "test", ip: "127.0.0.1", added: new Date(), lastseen: new Date())
        Report newAlarm = new Report(time: new Date(), type: "type", request: "GET /../../", status: "OPEN", attacker: "127.0.0.1")
        User newUser = new User(name: "admin", password: "password", status: "admin", domain: "default")
        ConfigHG newConfig = new ConfigHG(nameMandant: "default",
                                                    changedFromIP: "127.0.0.1",
                                                    added: new Date(),
                                                    lastChanged: new Date(),
                                                    useTelegram: "no",
                                                    useTwitter: "no",
                                                    useImage: "no",
                                                    telegramToken: "Telegram Token",
                                                    twitterToken: "Twitter Token",
                                                    image: "dtag",
                                                    usePushover: "no",
                                                    pushoverToken: "Pushover Token")


        newConfig.save(flush:true)
        honey.save(flush:true)
        ui.save(flush:true)
        newAlarm.save(flush:true)
        newUser.save(flush: true)

    }



    def destroy = {
    }
}
