import honeyalarmg2.Report
import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport
import honeyalarmg2.ConfigHG
import honeyalarmg2.Role
import honeyalarmg2.UserRole
import honeyalarmg2.User


class BootStrap {

    def init = { servletContext ->


        //
        // init some dummy variables
        //

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        Honeypot honey = new Honeypot(name: "demohoneypot", password: "test", ip: "127.0.0.1", added: new Date(), lastseen: new Date())
        Report newAlarm = new Report(time: new Date(), type: "type", request: "GET /../../", status: "OPEN", attacker: "127.0.0.1")
        //UserOld newUser = new UserOld(name: "admin", password: "password", status: "admin", domain: "default")
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
                                                    pushoverToken: "Pushover Token",
                                                    telegramUsers: new LinkedList(),
                                                    twitterOAuthConsumerKey: "...",
                                                    twitterOAuthConsumerSecret: "...",
                                                    twitterOAuthAccessToken: "...",
                                                    twitterOAuthAccessTokenSecret: "...")


        newConfig.save(flush:true)
        honey.save(flush:true)
        ui.save(flush:true)
        newAlarm.save(flush:true)



        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()

        def testUser = new User('me', 'password').save()

        UserRole.create testUser, adminRole, true

    }



    def destroy = {
    }
}
