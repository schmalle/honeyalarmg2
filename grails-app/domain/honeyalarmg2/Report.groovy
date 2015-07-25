package honeyalarmg2

class Report
{

    //
    // A report is the object coming from the user honeypots
    // confirmed
    // STATUS = OPEN
    // STATUS = FALSE_POSTITIVE
    //

    String time
    String type
    String request
    String status
    String attacker;

    static constraints = {
    }
}
