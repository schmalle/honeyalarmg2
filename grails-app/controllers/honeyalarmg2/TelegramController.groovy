package honeyalarmg2

import grails.converters.JSON
import groovy.json.JsonSlurper
import groovyx.net.http.HTTPBuilder


class TelegramController
{


    def index()
    {

    }

    def getMe()
    {

    }

    /**
     * sends a message via the telegram message protocol
     * @param token
     * @param text
     * @param chatid
     * @return
     */
    def sendMessage(token, text, chatid)
    {
        def url = "https://api.telegram.org"
        def uriPath = "/bot" + token + "/sendMessage"

        HTTPBuilder http = new HTTPBuilder(url)
        def content = "chat_id=" + chatid + "&text=" + text

        http.post(path:uriPath, body:content) { resp, reader ->;
        //content will be url encoded
            println  reader.text
            println resp.statusLine
            assert resp.statusLine.statusCode == 200
        }
    }

    def getUpdates()
    {

    }

  }
