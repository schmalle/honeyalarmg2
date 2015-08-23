package honeyalarmg2

class ConfigController
{

    def saveConfig()
    {

        ConfigHG x = new ConfigHG(params)
        x.lastChanged = (String)new Date()
        x.changedFromIP = request.getRemoteAddr()

        // find old database entry
        ConfigHG update = ConfigHG.findByNameMandant(x.nameMandant)
        update.delete()

        x.save(flush: true)

        redirect(controller: "IndexAngular", action: "index")
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

        [config: config, size:size, telegramID:telegramID, telegramFirstName:telegramFirstName, telegramLastName:telegramLastName, ip: request.getRemoteAddr()  ]
    }
}
