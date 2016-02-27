package honeyalarmg2

class HeaderGeneratorController
{

    def index() {

        def numberOfReports = Report.all.size()
        def numberOfAlarms = Alarm.all.size()
        def numberOfOpenAlarms = Report.findAllByStatus("OPEN").size()
        // extract pure numbers for display
        def numberOfHoneypots = Honeypot.all.size()

        [honeypots:numberOfHoneypots, numberOfAlarms : numberOfAlarms, numberOfReports : numberOfReports]
    }
}
