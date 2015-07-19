package honeyalarmg2

class IndexAngularController
{

    /*

        The index class passes only core information to the clients

     */

    def index()
    {

        // extract pure numbers for display
        def honeypots = Honeypot.all.size()
        def alarms = Report.all.size()
        def ips = IP.all.size()

        // extract data arrays
        def reports = UIReport.findAllByTimeIsNotNull()

        [honeypots:honeypots, alarms:alarms, reports: reports, ips: ips]
    }
}
