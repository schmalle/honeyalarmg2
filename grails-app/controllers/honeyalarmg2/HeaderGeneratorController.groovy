package honeyalarmg2

class HeaderGeneratorController
{

    def index() {

        def numberOfAlarms = Report.all.size()
        def numberOfOpenAlarms = Report.findAllByStatus("OPEN").size()

        [numberOfAlarms : numberOfAlarms, numberOfOpenAlarms : numberOfOpenAlarms]
    }
}
