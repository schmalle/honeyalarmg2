package honeyalarmg2

import org.springframework.security.access.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
class ShowAlarmsController
{

    def index() {
        def reports = Alarm.findAll()
        [reports:reports]
    }
}
