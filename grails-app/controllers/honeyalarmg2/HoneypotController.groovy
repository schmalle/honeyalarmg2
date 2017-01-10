package honeyalarmg2

import org.springframework.security.access.annotation.Secured
import org.codehaus.groovy.grails.validation.routines.InetAddressValidator
import org.apache.http.HttpEntity
import org.apache.http.HttpResponse
import org.apache.http.client.HttpClient
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.DefaultHttpClient


@Secured(["ROLE_ADMIN", "ROLE_USER", "ROLE_ANONYMOUS"])
class HoneypotController {

    transient springSecurityService
    transient grailsApplication
    transient twitterService
    transient eWSService
    transient slackService

    @Secured(["ROLE_ADMIN"])
    def delete() {

        def honeypot = Honeypot.findById(params.id)
        if (honeypot != null)
        {
            Console.println("Info: Honeypot already existing")
            honeypot.delete(flush: true)
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot " + params.id + " deleted"])
        }
        else
        {
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot " + params.id + " not found"])
        }
    }

    //
    // update UI
    //
    def updateUIReport(report)
    {
        def reports = UIReport.findAll()
        if (reports.size() < 8)
            report.save()
        else {

            UIReport rep0 = reports.get(0)
            rep0.delete(flush: true)
            report.save(flush: true)

        }

    }

    //
    // saves a new honeypot instance if not already existing
    //
    def saveHoneypot() {

        String name = params.name

        def honeypot = Honeypot.findByName(name)
        if (honeypot != null)
        {
            Console.println("Info: Honeypot already existing")
            redirect  (controller: "Index" , action:"index", params: [alertText: "Honeypot name already existing"])
        }
        else
        {
            honeypot = new Honeypot(params)

            // fill necessary dummy data
            honeypot.ip = "new"
            honeypot.lastseen = "new"
            honeypot.save(flush: true)
            redirect  (controller: "Honeypot" , action:"index")
        }
    }

    /*
        handle the periodical update request from the honeypots
     */

    def updateIP() {


        def xmlText = request.reader.text
        def xmlData = new XmlSlurper().parseText(xmlText)
        java.lang.String username = xmlData.Authentication.username
        java.lang.String token = xmlData.Authentication.token
        java.lang.String analyzerID = xmlData.Authentication.analyzername

        User user = User.findByUsername(username)
        String tokenFromDatabase = user.pwbackup
        token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(token);

        if (!user)
            return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")

        def match = token == tokenFromDatabase

        if (!match)
            return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")


        def honeypot = Honeypot.findByName(analyzerID)
        if (!honeypot) {
            UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Keep alive call from unknown honeypot " + analyzerID)
            updateUIReport(newHoneypotUpdate)

            def requestViaProxy = request.getHeader("X-Forwarded-For")
            if (!requestViaProxy) {
                requestViaProxy = request.remoteAddr
            }

            Honeypot analyzer = new Honeypot(ip: requestViaProxy, added: new Date(), lastseen: new Date(), name: analyzerID)
            analyzer.save(flush: true)

            newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Created new honeypot " + analyzerID)
            updateUIReport(newHoneypotUpdate)

            return renderPlainText("not ok")
        }

        honeypot.delete()
        honeypot.properties['ip'] = request.remoteAddr
        honeypot.properties['lastseen'] = new Date()
        honeypot.save()

        //
        // generate update entry for ui
        //
        UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "Keep alive call from honeypot " + analyzerID)
        updateUIReport(newHoneypotUpdate)

        return renderPlainText("ok")

    }   // updateIP



    /*
        Update attacking IPs
     */
    def ipUpdate(source)
    {
        def ip



        def reports = IP.findAll("from IP as b where b.text='" + source + "'")
        if (reports.size() == 0)
            ip = new IP(text: "" + source, firstSeen: new Date(), lastSeen: new Date())
        else
            ip = reports.get(0)

        ip.lastSeen = new Date()

        ip.save(flush: true)


    }

    /*
        update or creates an analzer with ip adress and first seen and last seen dates
        code checks for proxy / forwarded for requests
     */
    def updateCreateAnalyzer(analyzerID) {

        def analyzer = Honeypot.findByName(analyzerID)
        if (!analyzer)
        {
            UIReport newHoneypotUpdate = new UIReport(type: "INFO", time: new Date(), text: "First call from honeypot " + analyzerID + "(creating honeypot)")
            updateUIReport(newHoneypotUpdate)

            def requestViaProxy = request.getHeader("X-Forwarded-For")
            if (!requestViaProxy) {
                requestViaProxy = request.remoteAddr
            }

            analyzer = new Honeypot(ip: requestViaProxy, added: new Date(), lastseen: new Date(), name: analyzerID)
            analyzer.save(flush: true)

        }
        else {
            analyzer.delete()
            analyzer.properties['ip'] = request.remoteAddr
            analyzer.properties['lastseen'] = new Date()
            analyzer.save()
        }

    }


    def retrieveAlertTypeURL(node, requestURL, alertType) {


        node.Request.each { requestNode->

            def type = requestNode.@type
            print "Found type in alert: " + type

            def test = type == 'url'

            if (test)
            {
                requestURL = requestNode.text()
                print "Found URL Type : " + type
                alertType = "WEB"
            }

        }

        return [requestURL, alertType]
    }


    def report()
    {
        def xmlText = request.reader.text
        def xmlData = new XmlSlurper().parseText(xmlText)
        def username = xmlData.Authentication.username

        //byte[] encoded0 = xmlText.encodeBase64()
        byte[] encoded = new org.apache.commons.codec.binary.Base64().encodeBase64(xmlText.bytes)

        java.lang.String token = xmlData.Authentication.token

        User user = User.findByUsername(username)
        String tokenFromDatabase = user.pwbackup
        token = org.apache.commons.codec.digest.DigestUtils.sha256Hex(token);

        if (!user)
            return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")

        def match = token == tokenFromDatabase

       if (!match)
           return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>FAILED</StatusCode><Text></Text></Result>")

        xmlData.Alert.each { node->

            java.lang.String requestURL = "<UNDEFINED>"
            java.lang.String alertType = "BINARY"
            java.lang.String attackSource = node.Source.text()

            java.lang.String analyzerID = node.Analyzer.@'id'
            print "Data from analyzer id" + analyzerID

            updateCreateAnalyzer(analyzerID)
            (requestURL, alertType) = retrieveAlertTypeURL(node, requestURL, alertType)

            print "Data from alert" + requestURL + " " + alertType

            //
            // only show reports, which are not already in an ignored list
            //
            if (InetAddressValidator.getInstance().isValid(attackSource) && notIgnored(requestURL))
            {

                ConfigHG config = ConfigHG.findAll().get(0)
                List<User> userList = User.findAll()

                if (config.useTwitter == "yes")
                    twitterService.directMessageList(userList, "New alarm from Hp reporting UI")

                if (config.useSlack == "yes")
                    slackService.send { text "New alarm from Hp reporting UI"}


                //
                // generate update entry for ui
                //
                Report newReport = new Report(encoded: encoded, type: alertType, time: new Date(), request: "" + requestURL, status: "OPEN", attacker: "" + attackSource)
                newReport.save(flush: true)

                UIReport newHoneypotUpdate = new UIReport(type: "ALARM", time: "" + new Date(), text: "Alarm call from honeypot " + analyzerID)
                updateUIReport(newHoneypotUpdate)

                ipUpdate(attackSource)

            }


        }   // parsing each alert !!!


        sendTacho(xmlText)

        return renderPlainText("<?xml version=\"1.0\" encoding=\"UTF-8\" ?><Result><StatusCode>OK</StatusCode><Text></Text></Result>")

    }


    /**
     * send data to tacho, if given in config
     * @param xmlText
     * @return
     */
    def sendTacho(xmlText) {
        if (grailsApplication.config.useSicherheitstacho.contains("no")) {


            int start = xmlText.indexOf("<Authentication>")
            int end = xmlText.indexOf("</Authentication>")


            String targetString = "<EWS-SimpleMessage version=\"2.0\"><Authentication><username>" + grailsApplication.config.userNameTSecRadar + "</username><token>" +  grailsApplication.config.passwordTSecRadar + "</token>"
            String comText = targetString + xmlText.substring(end)


            def returnText = sendHttps(grailsApplication.config.serverTSecRadar, comText)
            print returnText

        }
    }   // sendTacho



    boolean notIgnored(requestURL) {

        java.lang.String conv = requestURL
        def checksum = org.apache.commons.codec.digest.DigestUtils.sha256Hex(conv)

        def ignore = IgnoredRequests.findByChecksum(checksum)

        return (ignore == null)
    }


    //
    // render plain text
    //
    def renderPlainText(returnText)
    {
        return render(text: returnText, contentType: "text/plain", encoding: "UTF-8")
    }


    //
    // show index page
    //
    def index()
    {
        def Honeypots = Honeypot.findAll()
        def dateAdded = new Date()

        //
        // retrieve current role and pass it to UI
        //
        String role = "ROLE_ANONYMOUS"
        String[] roles = springSecurityService.getPrincipal().getAuthorities()

        if (roles.length == 1)
            role = roles[0]


        [role: role, Honeypots: Honeypots, dateAdded: dateAdded]
    }

    def sendHttps(String httpUrl, String data) {
        HttpClient httpClient = new DefaultHttpClient()
        HttpResponse response
        try {
            HttpPost httpPost = new HttpPost(httpUrl)
            httpPost.setHeader("Content-Type", "text/xml")

            HttpEntity reqEntity = new StringEntity(data, "UTF-8")
            reqEntity.setContentType("text/xml")
            reqEntity.setChunked(true)

            httpPost.setEntity(reqEntity)
            log.info "executing request " + httpPost.getRequestLine()

            response = httpClient.execute(httpPost)
            HttpEntity resEntity = response.getEntity()

            log.info response.getStatusLine()
            if (resEntity != null) {
                log.with {
                    info "Response content length: " + resEntity.getContentLength()
                    if (isDebugEnabled()) {
                        debug "Response Chunked?: " + resEntity.isChunked()
                        debug "Response Encoding: " + resEntity.contentEncoding
                        debug "Response Content: " + resEntity.content.text
                    }
                }
            }
            // EntityUtils.consume(resEntity);
        }
        finally {
            // When HttpClient instance is no longer needed,
            // shut down the connection manager to ensure
            // immediate deallocation of all system resources
            httpClient.getConnectionManager().shutdown()
        }
        return response.getStatusLine()
    }


}
