package honeyalarmg2

import org.springframework.security.access.annotation.Secured

class TwitterController
{
    def twitterService

    @Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
    def index() {

        twitterService.directMessage("flakedev", "just a test")

        render ("test")
    }
}
