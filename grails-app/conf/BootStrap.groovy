import honeyalarmg2.Report
import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport

class BootStrap {

    def init = { servletContext ->

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        Honeypot honey = new Honeypot(name: "demohoneypot", password: "test", ip: "127.0.0.1", added: new Date(), lastseen: new Date())
        Report newAlarm = new Report(time: new Date(), type: "type", request: "DUMMY", status: "DEBUG", attacker: "127.0.0.1")

        honey.save(flush:true)
        ui.save(flush:true)
        newAlarm.save(flush:true);

    }



    def destroy = {
    }
}
