package honeyalarmg2

//import org.springframework.security.access.annotation.Secured

class IndexController
{

    /*

        The index class passes only core information to the clients

     */
    def index()
    {

        // extract pure numbers for display
        def numberOfHoneypots = Honeypot.all.size()

        def numberOfIps = IP.all.size()

        // extract data arrays
        //def reports = UIReport.findAllByTimeIsNotNull()

        [honeypots:numberOfHoneypots, ips: numberOfIps]
    }
}
