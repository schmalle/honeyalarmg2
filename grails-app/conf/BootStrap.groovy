import honeyalarmg2.Report
import honeyalarmg2.Honeypot
import honeyalarmg2.UIReport
import honeyalarmg2.User

class BootStrap {

    def init = { servletContext ->

        UIReport ui = new UIReport(time: new Date(), type:"INFO", text:'UI started')
        Honeypot honey = new Honeypot(name: "demohoneypot", password: "test", ip: "127.0.0.1", added: new Date(), lastseen: new Date())
        Report newAlarm = new Report(time: new Date(), type: "type", request: "GET /../../", status: "OPEN", attacker: "127.0.0.1")
        User newUser = new User(name: "admin", password: "password", status: "admin")

        honey.save(flush:true)
        ui.save(flush:true)
        newAlarm.save(flush:true)
        newUser.save(flush: true)

    }



    def destroy = {
    }
}
