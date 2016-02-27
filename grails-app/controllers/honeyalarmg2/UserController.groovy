package honeyalarmg2

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_ADMIN")
class UserController {


    def newUser() {


        def username = params.username
        def password = params.password
        def twitterName = params.twitterName
        def role = params.role

        def testUser = new User(username: params.username, password:  params.password, pwbackup: "blah", twitterName: params.twitterName, role: params.role).save(flush: true)


        //
        System.out.println("heklp")

    }

    def index() {
        List users = User.findAll()
        [users: users]

    }

    def add()
    {

    }

    def del()
    {

    }
}

