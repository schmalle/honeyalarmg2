package honeyalarmg2


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ReportController
{

    static allowedMethods = [moveToAlarm: "GET", save: "POST", update: "PUT", delete: "DELETE"]


    def moveToAlarm()
    {
        def myReport = Report.findById(params.id)

        Alarm myAlarm = new Alarm(time: myReport.time, type: myReport.type, request: myReport.request, attacker: myReport.attacker)
        myAlarm.save()

    }

    def index(Integer max)
    {
        params.max = Math.min(max ?: 10, 100)
        respond Report.list(params), model: [reportInstanceCount: Report.count()]
    }

    def show(Report reportInstance)
    {
        respond reportInstance
    }

    def create()
    {
        respond new Report(params)
    }

    @Transactional
    def save(Report reportInstance)
    {
        if (reportInstance == null)
        {
            notFound()
            return
        }

        if (reportInstance.hasErrors())
        {
            respond reportInstance.errors, view: 'create'
            return
        }

        reportInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'report.label', default: 'Report'), reportInstance.id])
                redirect reportInstance
            }
            '*' { respond reportInstance, [status: CREATED] }
        }
    }

    def edit(Report reportInstance)
    {
        respond reportInstance
    }

    @Transactional
    def update(Report reportInstance)
    {
        if (reportInstance == null)
        {
            notFound()
            return
        }

        if (reportInstance.hasErrors())
        {
            respond reportInstance.errors, view: 'edit'
            return
        }

        reportInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Report.label', default: 'Report'), reportInstance.id])
                redirect reportInstance
            }
            '*' { respond reportInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Report reportInstance)
    {

        if (reportInstance == null)
        {
            notFound()
            return
        }

        reportInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Report.label', default: 'Report'), reportInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound()
    {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'report.label', default: 'Report'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
