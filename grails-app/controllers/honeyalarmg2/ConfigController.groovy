package honeyalarmg2

class ConfigController
{

    def index()
    {
        ConfigHG config = ConfigHG.findByNameMandant("default")
        def size = ConfigHG.all.size()
        [config: config]
    }
}
