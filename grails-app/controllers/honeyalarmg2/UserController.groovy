package honeyalarmg2

import grails.plugin.springsecurity.annotation.Secured

@Secured("ROLE_ADMIN")
class UserController {


    def newUser() {


        def username = params.username
        def password = params.password
        def twitterName = params.twitterName
        java.lang.String role = params.role

        def testUser = new User(username: params.username, password:  params.password, pwbackup: "blah", twitterName: params.twitterName, role: params.role).save(flush: true)

        def adminRole = new Role('ROLE_ADMIN').save()
        def userRole = new Role('ROLE_USER').save()


        if (role.compareToIgnoreCase("admin"))  {
            UserRole.create testUser, adminRole, true
        }
        else {
            UserRole.create testUser, userRole, true
        }

        redirect  (controller: "index" , action:"index")

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

