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

        def configList = ConfigHG.findAll()
        ConfigHG config = configList.get(0)

        if (!config)
            config.delete(flush: true)


        // find old database entry
        ConfigHG update = new ConfigHG(params)
        update.id = 1
        update.lastChanged = (String)new Date()
        update.changedFromIP = request.getRemoteAddr()
        update.save()

        redirect(controller: "Index", action: "index")
    }

    @Secured("ROLE_ADMIN")
    def index()
    {

        def configList = ConfigHG.findAll()
        ConfigHG config = configList.get(0)

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
