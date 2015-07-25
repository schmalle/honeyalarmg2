package honeyalarmg2

class User {

    String name
    String password
    String status               // user / admin
    String domain

    static constraints = {

        name(nullable:false)
        password(nullable:false)
        status(nullable: false)
        domain(nullable: false)
    }
}
