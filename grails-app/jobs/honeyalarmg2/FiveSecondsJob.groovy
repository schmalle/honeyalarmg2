package honeyalarmg2



class FiveSecondsJob {
    static triggers = {
      simple repeatInterval: 6*5000l // execute job once in 5 seconds
    }

    def execute() {

     //   def config = ConfigHG.findByNameMandant("default")

    }
}
