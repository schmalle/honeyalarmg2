package honeyalarmg2

import grails.util.Holders

class GraphController
{


    def index() {

        def twitter = Holders.grailsApplication.mainContext.getBean 'TwitterService'
        twitter.tweet("just a demo")

    }
}
