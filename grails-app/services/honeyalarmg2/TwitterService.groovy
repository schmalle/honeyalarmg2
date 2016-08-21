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

    def createConfigurationBuilder()
    {

        ConfigHG config = ConfigHG.findAll().get(0)

        if (config.useTwitter == "yes")
        {

            ConfigurationBuilder cb = new ConfigurationBuilder();
            cb.setDebugEnabled(true)
                    .setOAuthConsumerKey(config.twitterOAuthConsumerKey)
                    .setOAuthConsumerSecret(config.twitterOAuthConsumerSecret)
                    .setOAuthAccessToken(config.twitterOAuthAccessToken)
                    .setOAuthAccessTokenSecret(config.twitterOAuthAccessTokenSecret);

            return cb

        }

        return null
    }


    def tweet(String latestStatus)
    {

        def config = createConfigurationBuilder()
        if (config)
        {

            TwitterFactory tf = new TwitterFactory(config.build());
            Twitter twitter = tf.getInstance();

            Status status = twitter.updateStatus(latestStatus);
            System.out.println("Successfully updated the status to [" + status.getText() + "].");
        }
    }



    def directMessageList(List<User> recipients, String text)
    {

        for(user in recipients){

                if (!user.twitterName.contains("<none>")) {
                    directMessage(user.twitterName, text)
                }
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
