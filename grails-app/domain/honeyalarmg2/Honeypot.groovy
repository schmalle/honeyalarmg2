package honeyalarmg2

class Honeypot {

    String name
    String password
    String ip
    String added

    static constraints = {
        name(nullable: false)
        password(nullable: false)
        ip(nullable: false)
        added(nullable: false)
    }
}
