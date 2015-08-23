package honeyalarmg2

import grails.converters.JSON
import groovy.json.JsonSlurper
import groovyx.net.http.ContentType
import groovyx.net.http.HTTPBuilder
import groovyx.net.http.Method
import org.codehaus.groovy.grails.web.json.JSONObject


class TelegramController
{


    def urlBasis = "https://api.telegram.org"



    /**
     * returns the id the user represented by the token
     * @param token
     * @return
     */
    def getMe(token)
    {

        def url = "https://api.telegram.org"
        def uriPath = "/bot" + token + "/getMe"


        def http = new HTTPBuilder(url)

        http.request(Method.POST, ContentType.TEXT) {
            uri.path = uriPath
            headers.Accept = 'application/json'

            response.success = { resp, reader ->

                //println reader.text;
                println "response status: ${resp.statusLine}"

                JSONObject userJson = JSON.parse(reader.text)

                def data = userJson.values().iterator().next()
                return data.id

            }

        }

     }

    /**
     * returns the chat ids for a given user id described by a token
     * @param token
     * @return
     */
    def getChatIDBasis(token)
    {

        def uriPath = "/bot" + token + "/getUpdates"
        def slurper = new JsonSlurper()

        def http = new HTTPBuilder(urlBasis)

        try
        {

            http.request(Method.POST, ContentType.TEXT) {
                uri.path = uriPath
                headers.Accept = 'application/json'

                response.success = { resp, reader ->

                    //println reader.text;
                    println "response status: ${resp.statusLine}"

                    def response = reader.text

                    println "reader text" + response

                    def result = slurper.parseText(response)

                    def id = result.result.message.chat.id
                    def firstName = result.result.message.chat.first_name
                    def lastName = result.result.message.chat.last_name

                    //
                    // this may be only element or an array
                    //
                    return [id, firstName, lastName]

                }

            }
        }
        catch (Exception e)
        {
            return [null, null, null]
        }

        //  https://api.telegram.org/bot$1/getUpdates
    }


    def sendMessage()
    {
    }


    /**
     * sends a message to all chat parties
     * @param text
     * @param token
     * @return
     */
    def sendMessageAllChats(text, token)
    {
        def (id, firstNames, lastNames) = getChatIDBasis(token)

        id.each {
            def oneId = it
            sendMessageCore(token, text, oneId)
        }

    }


    /**
     *
     * @param text
     * @param token
     * @id    chat id to be addresed
     * @return
     */
    def sendMessageOne(text, token, id)
    {
            sendMessageCore(token, text, id)
    }




    /**
     * sends a message via the telegram message protocol
     * @param token
     * @param text
     * @param chatid
     * @return
     */
    def sendMessageCore(token, text, chatid, verbose)
    {

        def uriPath = "/bot" + token + "/sendMessage"
        def content = "?chat_id=" + chatid + "&text=" + URLEncoder.encode(text, "UTF-8")
        def http = new HTTPBuilder(urlBasis + uriPath + content)

        // perform the needed request
        http.request(Method.GET) {


            headers.Accept = 'application/json'

            response.success = { resp, reader ->

                def answer = reader.text
                def status = ${resp.statusLine}

                if (verbose)
                {
                    println "reader:text " + answer
                    println "response status: " + status
                }

                return status

            }

        }   // response.success
    }


  }
