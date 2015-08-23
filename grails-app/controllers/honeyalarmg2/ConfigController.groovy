package honeyalarmg2

class ConfigController
{

    /**
     * save configuration to database
     * @return
     */
    def saveConfig()
    {
        def enu = request.getParameterMap();
        String msg = "";

        for (def obj : enu) {
            msg += "\n<br/> Object key: " + obj.key;
            msg += "\n<br/> Object value: " + obj.value[0]; // [0] seems to remove some problematic square brackets that wrap around the value
            msg += "\n<br/> ";
        }

        print msg

        ConfigHG x = new ConfigHG(params)
        x.lastChanged = (String)new Date()
        x.changedFromIP = request.getRemoteAddr()

        // find old database entry
        ConfigHG update = ConfigHG.findByNameMandant(x.nameMandant)
        update.delete()

        x.save(flush: true)

        redirect(controller: "Index", action: "index")
    }

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

        List teleGramList = new LinkedList()

        //
        // ugly Java code
        //
        for (int runner = 0; telegramID != null && runner <= telegramID.size() -1; runner++)
        {
            String id = telegramID.get(runner)
            String firstName = telegramFirstName.get(runner)
            String lastName = telegramLastName.get(runner)

            teleGramList.add(id + ";;" + firstName + ";;" + lastName)

        }

        [telegramList: teleGramList, config: config, size:size, telegramID:telegramID, telegramFirstName:telegramFirstName, telegramLastName:telegramLastName, ip: request.getRemoteAddr()  ]
    }
}
