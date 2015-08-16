package honeyalarmg2

class AjaxAlarmController
{

    def index()
    {
        //
        // maximal 7 reports
        //
        def reports = UIReport.findAllByTimeIsNotNull([max: 7, sort: "id", order: "desc"])
        def config = ConfigHG.findByNameMandant("default")

        [reports: reports, config:config]

    }
}
