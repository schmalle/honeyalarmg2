package honeyalarmg2

import com.mysql.jdbc.Blob

class Report
{

    //
    // A report is the object coming from the user honeypots
    //
    // STATUS = CONFIRMED
    // STATUS = OPEN
    // STATUS = FALSE_POSTITIVE
    //

    String time
    String type
    String request
    String status
    String attacker
    byte[] encoded

    static constraints = {
        encoded(nullable: false, maxSize:1073741824)
    }


}
