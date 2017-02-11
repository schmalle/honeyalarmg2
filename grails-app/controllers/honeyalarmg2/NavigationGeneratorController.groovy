package honeyalarmg2

class NavigationGeneratorController
{

    def springSecurityService

    def index()
    {

        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]


        def configList = ConfigHG.findAll()
        ConfigHG config = configList.get(0)

        [config : config, role: role]
    }
}
