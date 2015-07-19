package honeyalarmg2

class UIReport {

    String time
    String type
    String text

    //
    //
    //

    static constraints = {
        time(nullable: false)
        type(nullable: false)
        text(nullable: false)
    }
}
