import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport

class BootStrap {

    def init = { servletContext ->

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        Honeypot honey = new Honeypot(name: "demohoneypot", password: "test", ip: "127.0.0.1", added: new Date(), lastseen: new Date())
        honey.save()
        ui.save()

    }



    def destroy = {
    }
}
