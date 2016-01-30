package honeyalarmg2

import org.springframework.security.access.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class LoginFakeController
{

    def index() {
        redirect(controller: "Index", action: "index")

    }
}
