package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class IndexController
{

    def springSecurityService



    /*

        The index class passes only core information to the clients

     */

    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
    def index()
    {

        def alertText = params.alertText;

        // extract pure numbers for display
        def numberOfHoneypots = Honeypot.all.size()

        def numberOfIps = IP.all.size()

        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]

        // extract data arrays
        //def reports = UIReport.findAllByTimeIsNotNull()

        [honeypots:numberOfHoneypots, ips: numberOfIps, alertText: alertText, role:role]
    }
}
