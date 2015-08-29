package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class AboutController {


    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
    def index() {}
}
