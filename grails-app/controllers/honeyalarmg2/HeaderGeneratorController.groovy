package honeyalarmg2

class HeaderGeneratorController
{

    def index() {

        def numberOfReports = Report.all.size()
        def numberOfAlarms = Alarm.all.size()
        def numberOfOpenAlarms = Report.findAllByStatus("OPEN").size()

        [numberOfAlarms : numberOfAlarms, numberOfReports : numberOfReports]
    }
}
