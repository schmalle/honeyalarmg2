package honeyalarmg2

import honeyalarmg2.Report
import org.springframework.security.access.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class HandleAlarmsController
{


    def index()
    {

        // extract data arrays
        def reports = Report.findByStatus('OPEN')

        [reports: reports]
    }
}
