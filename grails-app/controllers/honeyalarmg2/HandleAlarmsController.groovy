package honeyalarmg2

import honeyalarmg2.Report
import org.springframework.security.access.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class HandleAlarmsController
{


    def index()
    {

        def reports = Report.findAll("from Report as b where b.status='OPEN'")

        [reports: reports]
    }
}
