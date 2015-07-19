package honeyalarmg2

class IP
{

    String text
    String firstSeen
    String lastSeen

    static constraints = {
        text(nullable: false)
        firstSeen(nullable: false)
        lastSeen(nullable: false)
    }
}
