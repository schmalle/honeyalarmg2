package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class AboutController {

    def springSecurityService

    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
    def index() {

        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]

        [role: role]

    }
}
