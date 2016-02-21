package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class AlarmController
{

    @Secured("ROLE_ANONYMOUS")
    def index()
    {

        def reports = UIReport.findAll("from UIReport as b order by b.time",
                [max: 8, offset: 0])

        [reports: reports]

    }
}
