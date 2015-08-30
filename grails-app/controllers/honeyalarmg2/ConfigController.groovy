package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class ConfigController
{

    /**
     * save configuration to database
     * @return
     */
    @Secured("ROLE_ADMIN")
    def saveConfig()
    {
        def enu = request.getParameterMap();
        List telegramUser = new LinkedList()
        List infotelegramUser = new LinkedList()

        String msg = "";

        for (def obj : enu) {
            msg += "\n<br/> Object key: " + obj.key;
            msg += "\n<br/> Object value: " + obj.value[0]; // [0] seems to remove some problematic square brackets that wrap around the value
            msg += "\n<br/> ";

            String key = (String) obj.key
            String value = (String) obj.value[0]


            if (key.startsWith("useTelegram") && value.equalsIgnoreCase("yes"))
            {
                telegramUser.add(key.substring(11))
            }

            else if (key.startsWith("infoTelegram") && value.equalsIgnoreCase("yes"))
            {
                infotelegramUser.add(key)
            }
        }

        print msg

        ConfigHG x = new ConfigHG(params)
        x.telegramUsers = telegramUser
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
        ConfigHG config = ConfigHG.findByNameMandant("default")
        def size = ConfigHG.all.size()
        def telegramID = []
        def telegramFirstName = []
        def telegramLastName = []


        if (config == null)
        {
            println "bummer, config not found!!!"
        }

        if (config.useTelegram != null && config.useTelegram)
        {
            TelegramController x = new TelegramController()
            (telegramID, telegramFirstName, telegramLastName) = x.getChatIDBasis(config.telegramToken)
        }
        else
        {
            println "no telegram"
        }

        List teleGramList = config.telegramUsers

        //
        // ugly Java code
        //
        for (int runner = 0; telegramID != null && runner <= telegramID.size() -1; runner++)
        {
            String id = telegramID.get(runner)
            String firstName = telegramFirstName.get(runner)
            String lastName = telegramLastName.get(runner)

            // add entry only, if not visible yet
            if (!teleGramList.contains(id + ";;" + firstName + ";;" + lastName))
                teleGramList.add(id + ";;" + firstName + ";;" + lastName)

        }

        [telegramList: teleGramList, config: config, size:size, telegramID:telegramID, telegramFirstName:telegramFirstName, telegramLastName:telegramLastName, ip: request.getRemoteAddr()  ]
    }
}
