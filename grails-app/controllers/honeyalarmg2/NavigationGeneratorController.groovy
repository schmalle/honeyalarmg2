package honeyalarmg2

class NavigationGeneratorController
{

    def index()
    {
        ConfigHG config = ConfigHG.findByNameMandant("default")
        [config : config]
    }
}
