package honeyalarmg2

import honeyalarmg2.Report
import org.springframework.security.access.annotation.Secured
import org.apache.commons.codec.binary.Base64;


@Secured(["ROLE_ADMIN", "ROLE_USER"])
class HandleReportsController
{

    def index()
    {

        def reports = Report.findAll("from Report as b where b.status='OPEN'")
        def alertText = params.alertText

        [reports: reports, alertText: alertText]
    }


    def show()
    {
        def myReport = Report.findById(params.id)
        def alertText = "No reports / alarms available"


        if (myReport)
        {

            String testString = new String(myReport.encoded)

            byte[] decoded = Base64.decodeBase64(testString);
            alertText = new String(decoded, "UTF-8")

        }

        [alertText: alertText]

    }


}
