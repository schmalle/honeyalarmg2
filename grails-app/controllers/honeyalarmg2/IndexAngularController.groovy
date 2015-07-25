package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class IndexAngularController
{

    /*

        The index class passes only core information to the clients

     */
    @Secured(['IS_AUTHENTICATED_ANONYMOUSLY'])
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
