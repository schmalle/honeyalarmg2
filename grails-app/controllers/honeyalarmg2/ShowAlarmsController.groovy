package honeyalarmg2

import org.springframework.security.access.annotation.Secured

@Secured(["ROLE_ADMIN", "ROLE_USER"])
class ShowAlarmsController
{

    def index() {
        def reports = Alarm.findAll()
        [alarms:reports]
    }
}
