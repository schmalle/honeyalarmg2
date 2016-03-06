package honeyalarmg2

import org.apache.http.client.ResponseHandler
import org.apache.http.client.methods.HttpPost
import org.apache.http.entity.StringEntity
import org.apache.http.impl.client.BasicResponseHandler
import org.apache.http.impl.client.CloseableHttpClient
import org.apache.http.impl.client.HttpClients

class EWSService {


    def sendAlarmViaHoneyAlarm(java.lang.String request)
    {

        def userName = "<username>"
        def userNameClose = "</username>"
        def token = "<token>"
        def tokenClose = "</token>"


        ConfigHG config = ConfigHG.findAll().get(0)

        def startUsername = request.indexOf(userName) + userName.length()
        def closeUsername = request.indexOf(userNameClose)

        java.lang.String finalRequest = request.substring(0, startUsername) + config.userNameTSecRadar + request.substring(closeUsername)


        def startToken = request.indexOf(token) + token.length()
        def closeToken = request.indexOf(tokenClose)

        finalRequest += request.substring(0, startToken) + config.passwordTSecRadar + request.substring(closeToken)


        // username
        //    "<username>USER</username>" +
        //"<token>PASSWORD</token>" +
        // password

        pushAlarm(finalRequest, config.serverTSecRadar)

    }

    def sendAlarm(String time, String attackerIP, String request, String host)
    {

        ConfigHG config = ConfigHG.findAll().get(0)

        def requestData = getRequest(config.userNameTSecRadar, config.passwordTSecRadar, time, attackerIP, request, host);
        pushAlarm(requestData, config.serverTSecRadar)
    }


    /**
     *
     * @param requestData XML data to be send to the server
     * @param url URL of the EWS server (DTAG)
     */
    def pushAlarm(String requestData, String url)
    {

        try
        {
            HttpPost method = new HttpPost(url);

            CloseableHttpClient client = HttpClients.createDefault();

            StringEntity strent = new StringEntity(requestData);
            strent.setContentType("text/xml; charset=utf-8");
            method.setEntity(strent);
            ResponseHandler<String> response = new BasicResponseHandler();

            String returnCode = client.execute(method, response);

            System.out.println(new Date().toString() + ": Answer from server: " + returnCode);

        }
        catch (Exception e)
        {
            System.out.println("Error at EWSClient.fetch(" + new Date().toString() + "): Exception caught");
        }

    }	// fetchIPsfromCore


    /**
     *
     * @param username  username for DTAG EWS
     * @param password password / token for DTAG EWS
     * @param time  time of attack
     * @param sourceIP  source of the attack
     * @param requestData   request string of the attack
     * @param host  my host
     * @return  ready request
     */
    def getRequest(String username, String password, String time, String sourceIP, String requestData, String host)
    {

        try
        {

            String ewsRequest = "" +
                    "<EWS-SimpleMessage version=\"2.0\">\n" +
                    "<Authentication>\n" +
                    "<username>USER</username>" +
                    "<token>PASSWORD</token>" +
                    "</Authentication>" +

                    "<Alert>" +
                    "<Analyzer id=\"4711\"/>" +
                    "<CreateTime tz=\"+0200\">TIME</CreateTime>" +
                    "<Source category=\"ipv4\" port=\"\" protocol=\"tcp\">SOURCE_IP</Source>" +
                    "<Target category=\"ipv4\" port=\"9200\" protocol=\"tcp\">1.2.3.4</Target>" +
                    "<Request type=\"url\">PLAIN</Request>" +
                    "<Request type=\"raw\"></Request>" +
                    "<Request type=\"description\">ElasticSearch%20Honeypot</Request>" +
                    "<AdditionalData meaning=\"host\" type=\"string\">HOST</AdditionalData>" +
                    "</Alert>" +

                    "</EWS-SimpleMessage>\r\n\r\n";

            ewsRequest = ewsRequest.replace("USER", username);
            ewsRequest = ewsRequest.replace("PASSWORD", password);
            ewsRequest = ewsRequest.replace("SOURCE_IP", sourceIP);
            ewsRequest = ewsRequest.replace("PLAIN", URLEncoder.encode(requestData, "UTF-8"));

            ewsRequest = ewsRequest.replace("HOST", host);
            ewsRequest = ewsRequest.replace("TIME", time); // URLEncoder.encode(time, "UTF-8"));


            return ewsRequest

        }
        catch (Exception e)
        {
            System.out.println("Error at EWSClient.getRequest(" + new Date().toString() + "): Exception caught");
            return null;
        }

    }   // getRequest

}
