package honeyalarmg2

class User {

    String name
    String password
    String status               // user / admin

    static constraints = {

        name(nullable:false)
        password(nullable:false)
        status(nullable: false)
    }
}
