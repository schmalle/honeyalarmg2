package honeyalarmg2

import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional()
class ReportController
{

    static allowedMethods = [moveToAlarm: "GET", save: "POST", update: "PUT", delete: "DELETE"]

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def moveToAlarm()
    {
        def myReport = Report.findById(params.id)



        Alarm myAlarm = new Alarm(time: myReport.time, type: myReport.type, request: myReport.request, attacker: myReport.encoded)
        myAlarm.save()
        myReport.delete()

        redirect(controller: "Index", action: "index")

    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def ignore()
    {
        def myReport = Report.findById(params.id)

        String checksum = org.apache.commons.codec.digest.DigestUtils.sha256Hex(myReport.request)


        def myIgnore = new IgnoredRequests(request: myReport.request, checksum: checksum)
        myIgnore.save()
        myReport.delete()

        redirect(controller: "Index", action: "index")

    }

    @Secured(["ROLE_ADMIN", "ROLE_USER"])
    def remove()
    {
        def myReport = Report.findById(params.id)
        myReport.delete()
        redirect(controller: "Index", action: "index")
    }


    def index(Integer max)
    {
        params.max = Math.min(max ?: 10, 100)
        respond Report.list(params), model: [reportInstanceCount: Report.count()]
    }

    def show()
    {
        def myReport = Report.findById(params.id)
        byte[] encoded = myReport.encoded
        String alertText =  encoded.decodeBase64().toString()

        redirect(controller: "Index", action: "index")

        //redirect(controller: "HandleReports", action: "index", params: [alertText: alertText])
    }

    def create()
    {
        respond new Report(params)
    }



}
