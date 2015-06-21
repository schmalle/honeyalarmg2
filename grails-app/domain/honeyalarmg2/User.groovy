package honeyalarmg2

class User {

    String name
    String password

    static constraints = {

        name(nullable:false)
        password(nullable:false)
    }
}
