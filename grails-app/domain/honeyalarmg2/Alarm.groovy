package honeyalarmg2

class Alarm {

    String time
    String type
    String request
    String sourceIP

    static constraints = {
        time(nullable: false)
        type(nullable: false)
        request(nullable: false)
    }
}
