package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class ConfigController
{

    def springSecurityService

    /**
     * save configuration to database
     * @return
     */
    @Secured("ROLE_ADMIN")
    def saveConfig()
    {


        ConfigHG x = new ConfigHG(params)
        x.lastChanged = (String)new Date()
        x.changedFromIP = request.getRemoteAddr()

        // find old database entry
        ConfigHG update = ConfigHG.findByNameMandant(x.nameMandant)
        update.delete()

        x.save(flush: true)

        redirect(controller: "Index", action: "index")
    }

    @Secured("ROLE_ADMIN")
    def index()
    {
        ConfigHG config = ConfigHG.find()

        if (config == null)
        {
            redirect  (controller: "Index" , action:"index", params: [alertText: "No config was found"])
            return
        }


        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]

        [role: role, config: config, ip: request.getRemoteAddr()  ]
    }
}
