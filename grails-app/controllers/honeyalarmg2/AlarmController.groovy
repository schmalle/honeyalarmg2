package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class AlarmController
{

    @Secured("ROLE_ANONYMOUS")
    def index()
    {
        //
        // maximal 6 reports
        //
        def reports = UIReport.findAllByTimeIsNotNull([max: 6, sort: "id", order: "desc"])
        def config = ConfigHG.findByNameMandant("default")

        [reports: reports, config:config]

    }
}
