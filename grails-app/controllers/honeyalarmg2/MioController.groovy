package honeyalarmg2

import org.springframework.security.access.annotation.Secured

@Secured("ROLE_ANONYMOUS")

class MioController
{

    def index() {

        def startTime = new Date().toGMTString()

        for (long i = 0; i <1000000; i++) {
            User x = User.findByUsername("me")
        }

        def endTime = new Date().toGMTString()

        System.out.println("Start: " + startTime + " End:" + endTime)

        [startTime: startTime, endTime: endTime]

    }
}
