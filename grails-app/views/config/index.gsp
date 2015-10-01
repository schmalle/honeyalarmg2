<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="honeyalarm">

<head>

    <style>

    .blank_row {
        height: 10px !important; /* Overwrite any previous rules */
        background-color: #FFFFFF;
    }

    </style>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Honeyalarm - Admin console</title>

    <!-- Bootstrap Core CSS -->
    <link href="${g.resource(dir: 'css', file: 'bootstrap.min.css')}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${g.resource(dir: 'css', file: 'metisMenu.css')}" rel="stylesheet">


    <!-- Timeline CSS -->
    <link href="${g.resource(dir: 'css', file: 'timeline.css')}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${g.resource(dir: 'css', file: 'sb-admin-2.css')}" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${g.resource(dir: 'css', file: 'morris.css')}" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${g.resource(dir: 'css', file: 'font-awesome.min.css')}" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div id="wrapper">

    <g:include controller="navigationGenerator" action="index"></g:include>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Configuration</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->



        <!-- /.row -->
        <div class="row">

            <!-- /.col-lg-8 -->
            <div>

                <!-- /.panel-heading -->
                <div>

                    <br>

                    <form action="/saveConfig" method="post">

                        <g:hiddenField name="nameMandant" value="${config.nameMandant}"/>
                        <g:hiddenField name="added" value="${config.added}"/>
                        <g:hiddenField name="ip" value="${ip}"/>


                        <table>

                            <tr>
                                <th></th>
                                <th></th>
                                <th></th>
                                <th></th>
                            </tr>



                            <tr><td>Created</td><td>${config.added}</td></tr>
                            <tr><td>Last changed</td><td>${config.lastChanged}</td></tr>

                            <tr class="blank_row">
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td><label>Use custom image</label></td>
                                <td><g:select id="useImage" name="useImage"
                                              from="${['no': 'No', 'yes': 'Yes']}"
                                              optionKey="key" optionValue="value"/></td>
                                <td><label>Image name</label></td>
                                <td><g:textField name="image" value="${config.image}"/></td>
                            </tr>

                            <tr>
                                <td><label>Use Twitter</label></td>
                                <td><g:select id="useTwitter" name="useTwitter"
                                              from="${['no': 'No', 'yes': 'Yes']}"
                                              optionKey="key" optionValue="value" value="${config.useTwitter}"/></td>
                                <td><label>Twitter Token</label></td>
                                <td><g:textField name="twitterToken" value="${config.twitterToken}"/></td>
                            </tr>



                            <tr>
                                <td><label> </label></td>
                                <td><label> </label></td>

                                <td><label>OAuthConsumerKey</label></td>
                                <td><g:textField name="twitterOAuthConsumerKey" value="${config.twitterOAuthConsumerKey}"/><td>

                            </tr>

                        <tr>
                            <td><label> </label></td>
                            <td><label> </label></td>

                            <td><label>OAuthConsumerSecret</label></td>
                            <td><g:textField name="twitterOAuthConsumerSecret" value="${config.twitterOAuthConsumerSecret}"/><td>

                            </tr>


                        <tr>
                            <td><label> </label></td>
                            <td><label> </label></td>

                            <td><label>twitterOAuthAccessToken</label></td>
                            <td><g:textField name="twitterOAuthAccessToken" value="${config.twitterOAuthAccessToken}"/><td>

                        </tr>

                        <tr>
                            <td><label> </label></td>
                            <td><label> </label></td>

                            <td><label>twitterOAuthAccessTokenSecret</label></td>
                            <td><g:textField name="twitterOAuthAccessTokenSecret" value="${config.twitterOAuthAccessTokenSecret}"/><td>

                        </tr>

                    </tr>


                            <tr class="blank_row">
                                <td colspan="3"></td>
                            </tr>
                            <tr class="blank_row">
                                <td colspan="3"></td>
                            </tr>
                            <tr>
                                <td><label>Use Telegram</label></td>
                                <td><g:select id="useTelegram" name="useTelegram"
                                              from="${['no': 'No', 'yes': 'Yes']}"
                                              optionKey="key" optionValue="value" value="${config.useTelegram}"/></td>


                                <td><label>Telegram Token:</label></td>
                                <td><g:textField name="telegramToken" value="${config.telegramToken}"/><br/></td>

                            </tr>

                            <!-- define needed users  -->

                            <g:each in="${telegramList}">

                                <tr>
                                    <td><label>Use ID ${it} (Telegram)</label></td>
                                    <td><g:select id="useTelegram${it}" name="useTelegram${it}"
                                                  from="${['no': 'No', 'yes': 'Yes']}"
                                                  optionKey="key" optionValue="value"/>
                                    </td>
                                    <td><label>Info Messages</label></td>
                                    <td><g:select id="use${it}" name="infoTelegram${it}"
                                                  from="${['no': 'No', 'yes': 'Yes']}"
                                                  optionKey="key" optionValue="value"/>
                                    </td>

                                </tr>
                            </g:each>

                            <tr class="blank_row">
                                <td colspan="3"></td>
                            </tr>

                        <tr>
                            <td><label>Use Pushover</label></td>
                            <td><g:select id="usePushover" name="usePushover"
                                          from="${['no': 'No', 'yes': 'Yes']}"
                                          optionKey="key" optionValue="value" value="${config.usePushover}"/></td>


                            <td><label>Pushover Token:</label></td>
                            <td><g:textField name="pushoverToken" value="${config.pushoverToken}"/><br/></td>

                        </tr>


                        <tr>
                                <td><input type="submit" class="btn btn-primary " value="Submit"></td>
                            </tr>

                        </table>
                    </form>

                    <br>  <br> <br>   <a href="/" class="btn btn-primary" role="button">Main</a>


                    <!-- /.panel -->

                    <!-- /.panel .chat-panel -->

                </div>
                <!-- /.col-lg-4 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

    <g:include controller="javascriptGenerator" action="index"></g:include>

</body>

</html>
