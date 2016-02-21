import honeyalarmg2.Report
import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport
import honeyalarmg2.ConfigHG
import honeyalarmg2.Role
import honeyalarmg2.UserRole
import honeyalarmg2.User


class BootStrap {

    def grailsApplication

    def init = { servletContext ->




        //
        // init some dummy variables
        //

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        ConfigHG newConfig = new ConfigHG(
                                                    changedFromIP: "<System>",
                                                    added: new Date(),
                                                    lastChanged: new Date(),
                                                    useTwitter: grailsApplication.config.useTwitter,
                                                    useImage: "no",
                                                    image: "dtag",
                                                    twitterUser: grailsApplication.config.twitterUser,
                                                    twitterOAuthConsumerKey: grailsApplication.config.twitterOAuthConsumerKey,
                                                    twitterOAuthConsumerSecret: grailsApplication.config.twitterOAuthConsumerSecret,
                                                    twitterOAuthAccessToken: grailsApplication.config.twitterOAuthAccessToken,
                                                    twitterOAuthAccessTokenSecret: grailsApplication.config.twitterOAuthAccessTokenSecret,

                                                    useSicherheitstacho: grailsApplication.config.useSicherheitstacho,
                                                    userNameTSecRadar: grailsApplication.config.userNameTSecRadar,
                                                    passwordTSecRadar: grailsApplication.config.passwordTSecRadar,
                                                    serverTSecRadar: grailsApplication.config.serverTSecRadar

        )


        newConfig.save(flush:true)
        ui.save(flush:true)

        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()

        def testUser = new User(username: 'me', password:  'password', pwbackup: "blah", twitterName: "none").save(flush: true)
        def testNormalUser = new User(username: 'meNormal', password:  'password', pwbackup: "blah", twitterName: "none").save(flush: true)


        UserRole.create testUser, adminRole, true
        UserRole.create testNormalUser, userRole, true

    }



    def destroy = {
    }
}

