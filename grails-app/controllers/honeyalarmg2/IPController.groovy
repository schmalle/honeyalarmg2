package honeyalarmg2

import org.springframework.security.access.annotation.Secured

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
@Transactional(readOnly = true)
class IPController
{

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index()
    {
        def ips = IP.findAll()
        [ips : ips]
    }


}
