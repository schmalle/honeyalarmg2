package honeyalarmg2

import grails.transaction.Transactional

import javax.net.ssl.HttpsURLConnection
import java.nio.charset.Charset

@Transactional
class PushOverService {

    def serviceMethod() {

    }

    /**
     * Sends a raw bit of text via POST to Pushover.
     * @param rawMessage
     * @return JSON reply from Pushover.
     * @throws IOException
     * (code derived from https://github.com/nicatronTg/jPushover/blob/master/src/me/shanked/nicatronTg/jPushover/Pushover.java)
     */
    def sendToPushoverRaw(String rawMessage) throws IOException {
        URL pushoverUrl = new URL("https://api.pushover.net/1/messages.json");

        HttpsURLConnection connection = (HttpsURLConnection) pushoverUrl.openConnection();
        connection.setDoOutput(true);
        connection.setDoInput(true);

        OutputStream outputStream = connection.getOutputStream();
        outputStream.write(rawMessage.getBytes(Charset.forName("UTF-8")));
        outputStream.close();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

        String output = "";
        String outputCache = "";
        while ((outputCache = br.readLine()) != null) {
            output += outputCache;
        }
        br.close();
        return output;
    }

    /**
     * Sends a message.
     * @param message The message to send.
     * @param title The title.
     * @param url A URL.
     * @param urlTitle A URL title.
     * @return JSON reply from Pushover.
     * @throws UnsupportedEncodingException
     * @throws IOException
     */
//    def sendMessage(String message, String title, String url, String urlTitle) throws UnsupportedEncodingException, IOException {
//        return sendToPushoverRaw(getAuthenticationTokens() + "&message=" + URLEncoder.encode(message, "UTF-8") + "&title=" + URLEncoder.encode(title, "UTF-8") + "&url=" + URLEncoder.encode(url, "UTF-8")
//                + "&url_title=" + URLEncoder.encode(urlTitle, "UTF-8"));
//    }


    /**
     * Gets a string with the auth tokens already made.
     * @return String of auth tokens
     * @throws UnsupportedEncodingException
     */
//    def getAuthenticationTokens(String userToken, String deviceID, String appToken) throws UnsupportedEncodingException{

//        if (deviceID != null) {
//            if (!(deviceID.trim() == "")) {
//                return "token=" + appToken + "&user=" + userToken + "&device=" + deviceID;
//            }
//        }

//        return "token=" + appToken + "&user=" + userToken;
//    }

}
