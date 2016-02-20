package honeyalarmg2

class Honeypot {

    //
    // Honeypot equals an analyzer in the DTAG early warning system
    //

    String name
    String ip
    String added
    String lastseen

    static constraints = {
        name(nullable: false)
        added(nullable: false)
        ip(nullable: false)
        lastseen(nullable: false)


    }
}
