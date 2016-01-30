package honeyalarmg2

import grails.transaction.Transactional
import twitter4j.DirectMessage
import twitter4j.Status
import twitter4j.Twitter
import twitter4j.TwitterException
import twitter4j.TwitterFactory
import twitter4j.conf.ConfigurationBuilder

@Transactional
class TwitterService
{

    def grailsApplication

    def createConfigurationBuilder()
    {

        String useTwitter = grailsApplication.config.useTwitter

        if (useTwitter == "yes")
        {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(grailsApplication.config.twitterOAuthConsumerKey)
                    .setOAuthConsumerSecret(grailsApplication.config.twitterOAuthConsumerSecret)
                    .setOAuthAccessToken(grailsApplication.config.twitterOAuthAccessToken)
                    .setOAuthAccessTokenSecret(grailsApplication.config.twitterOAuthAccessTokenSecret);

            return cb

        }

        return null
    }


    def tweet(String latestStatus)
    {

        def config = createConfigurationBuilder()
        if (config)
        {

            TwitterFactory tf = new TwitterFactory(cb.build());
            Twitter twitter = tf.getInstance();

            // The factory instance is re-useable and thread safe.
            //Twitter twitter = TwitterFactory.getSingleton();
            Status status = twitter.updateStatus(latestStatus);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        }
    }

    def directMessage(String recipient, String text)
    {


        ConfigurationBuilder cb = createConfigurationBuilder()


        if (cb)
        {
            TwitterFactory tf = new TwitterFactory(cb.build())
            Twitter twitter = tf.getInstance()

            try
            {
                DirectMessage message = twitter.sendDirectMessage(recipient, text);
                System.out.println("Direct message successfully sent to " + message.getRecipientScreenName());
            }
            catch (TwitterException te)
            {
                te.printStackTrace();
                System.out.println("Failed to send a direct message: " + te.getMessage());
            }

        }

    }

}
