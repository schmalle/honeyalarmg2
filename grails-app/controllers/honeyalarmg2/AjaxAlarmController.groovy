package honeyalarmg2

class AjaxAlarmController
{

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
