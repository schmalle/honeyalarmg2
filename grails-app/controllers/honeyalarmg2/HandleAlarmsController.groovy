package honeyalarmg2

import honeyalarmg2.Report

class HandleAlarmsController
{


    def index()
    {

        // extract data arrays
        def reports = Report.findByStatus('OPEN')

        [reports: reports]
    }
}
