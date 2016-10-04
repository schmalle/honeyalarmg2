# honeyalarmg2

IN DEVELOPMENT


Simplified UI for showing honeypot alarms for the DTAG early warning system (https://www.sicherheitstacho.eu)

Based on

- Bootstrap
- Grails 2.5.5
- great SBADMIN-2 Theme !
- Spring Security Code
- UlLink Slack client code (https://github.com/Ullink/simple-slack-api)


Functions

- collect alarms from your local honeypot installations
- review current alarms
- forward alarms to DTAG early warning system
- use role / security functions from Spring security
  Login username / password combination is (me/password)



see history.txt and todo.txt for details

Configfile (/etc/honeyalarmg2.properties)

- useTwitter=no
- twitterOAuthConsumerKey=X
- twitterOAuthConsumerSecret=A
- twitterOAuthAccessToken=Y
- twitterOAuthAccessTokenSecret=Y
- useSicherheitstacho=no
- userNameTSecRadar=community-01-user
- passwordTSecRadar=foth{a5maiCee8fineu7
- serverTSecRadar=https://community.sicherheitstacho.eu/ews-0.1/alert/postSimpleMessage
- usePushover=no
- pushOverAppToken=111
- pushOverDeviceID=222
- pushOverUserToken=333
- useSlack
- slackIncomingWebHooks



Thanks to

- Andrea De Pasquale for test/debug help (Telegram)
- Marco Ochse for Debugging
- Andre Vorbach for some ideas
- Rainer Schmidt for some suggestions


Some code examples taken from

https://forum.jquery.com/topic/refresh-div-tag-without-refresing-the-whole-page
http://spring.io/blog/2010/08/11/simplified-spring-security-with-grails/