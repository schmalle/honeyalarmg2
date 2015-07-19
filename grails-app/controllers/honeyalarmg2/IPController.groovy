package honeyalarmg2


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class IPController
{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max)
    {
        params.max = Math.min(max ?: 10, 100)
        respond IP.list(params), model: [IPInstanceCount: IP.count()]
    }

    def show(IP IPInstance)
    {
        respond IPInstance
    }

    def create()
    {
        respond new IP(params)
    }

    @Transactional
    def save(IP IPInstance)
    {
        if (IPInstance == null)
        {
            notFound()
            return
        }

        if (IPInstance.hasErrors())
        {
            respond IPInstance.errors, view: 'create'
            return
        }

        IPInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'IP.label', default: 'IP'), IPInstance.id])
                redirect IPInstance
            }
            '*' { respond IPInstance, [status: CREATED] }
        }
    }

    def edit(IP IPInstance)
    {
        respond IPInstance
    }

    @Transactional
    def update(IP IPInstance)
    {
        if (IPInstance == null)
        {
            notFound()
            return
        }

        if (IPInstance.hasErrors())
        {
            respond IPInstance.errors, view: 'edit'
            return
        }

        IPInstance.save flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'IP.label', default: 'IP'), IPInstance.id])
                redirect IPInstance
            }
            '*' { respond IPInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(IP IPInstance)
    {

        if (IPInstance == null)
        {
            notFound()
            return
        }

        IPInstance.delete flush: true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'IP.label', default: 'IP'), IPInstance.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NO_CONTENT }
        }
    }

    protected void notFound()
    {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'IP.label', default: 'IP'), params.id])
                redirect action: "index", method: "GET"
            }
            '*' { render status: NOT_FOUND }
        }
    }
}
