<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="honeyalarm">

<head>

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

        Created: ${config.added} <br>
        Last changed: ${config.lastChanged}

        <!-- /.row -->
        <div class="row">

            <!-- /.col-lg-8 -->
            <div>

                <!-- /.panel-heading -->
                <div>

                    <br>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" aria-label="...">
                                </span>
                                <input type="text" class="form-control" aria-label="..." value="Twitter auth token">
                            </div><!-- /input-group -->
                        </div><!-- /.col-lg-6 -->



                        <div class="col-lg-6">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" aria-label="...">
                                </span>
                                <input type="text" class="form-control" aria-label="..." value="Telegram auth token">
                            </div><!-- /input-group -->

                        </div><!-- /.col-lg-6 -->

                        <div class="col-lg-6">
                            <div class="input-group">
                                <span class="input-group-addon">
                                    <input type="checkbox" aria-label="...">
                                </span>
                                <input type="text" class="form-control" aria-label="..." value="Individual image">
                            </div><!-- /input-group -->

                        </div><!-- /.col-lg-6 -->

                        </div>

                        <br>
                        <br>

                        <a href="/" class="btn btn-primary " role="button">Submit</a>

                        <br>
                        <br>


                        <a href="/" class="btn btn-primary" role="button">Main</a>


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
