package honeyalarmg2

class Honeypot {

    String name
    String password
    String ip
    String added
    String lastseen

    static constraints = {
        name(nullable: false)
        password(nullable: false)
        ip(nullable: false)
        added(nullable: false)
        lastseen(nullable: false)
    }
}
