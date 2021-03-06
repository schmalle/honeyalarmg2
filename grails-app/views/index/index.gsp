<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
<!--    <meta http-equiv="refresh" content="10"> -->

    <title>Honeyalarm - Admin panel</title>

    <img src="http://www.sicherheitstacho.eu/static/images/headline_en.png" class="logo" alt="DTAG Dataview">


    <script src="${g.resource(dir: 'js', file:'jquery.min.js')}"></script>
    <script src="${g.resource(dir: 'js', file:'bootstrap.min.js')}"></script>
    <script src="${g.resource(dir: 'js', file:'bootbox.min.js')}"></script>

    <!-- Bootstrap Core CSS -->
    <link href="${g.resource(dir: 'css', file:'bootstrap.min.css')}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${g.resource(dir: 'css', file:'metisMenu.css')}" rel="stylesheet">


    <!-- Timeline CSS -->
    <link href="${g.resource(dir: 'css', file:'timeline.css')}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${g.resource(dir: 'css', file:'sb-admin-2.css')}" rel="stylesheet">


    <!-- Custom Fonts -->
    <link href="${g.resource(dir: 'css', file:'font-awesome.min.css')}" rel="stylesheet" type="text/css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->



    <script>

        $(document).ready(
                function() {
                    setInterval(function() {
                        var url = '/'; //please insert the url of the your current page here, we are assuming the url is 'index.php'
                        $('#div1-wrapper').load(url + ' #reportDiv'); //note: the space before #div1 is very important
                        $('#div0-wrapper').load(url + ' #alarmDiv'); //note: the space before #div1 is very important

                    }, 3000);
                });

    </script>



</head>

<body>

<script>
    bootbox.alert("${alertText}")
</script>

<div id="wrapper">

    <!-- Navigation -->
    <g:include controller="navigationGenerator" action="index"></g:include>



    <div id="page-wrapper">
        <div class="row">

            <div class="col-lg-12">
                <h1 class="page-header">Dashboard</h1>

            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->
        <div class="row">


            <!-- generate number of alarms dynamically every n seconds -->

        <div id="div0-wrapper">

            <!-- This div will be reloaded by Jquery every time -->
            <div id= "alarmDiv">

                <g:include controller="headerGenerator" action="index"></g:include>

            </div>
        </div>

        </div>
        <!-- /.row -->
        <div class="row">

            <!-- /.col-lg-8 -->
            <div>
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bell fa-fw"></i> Notifications Panel

                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">

                        <div id="div1-wrapper">


                            <!-- This div will be reloaded by Jquery every time -->
                            <div id= "reportDiv" class="list-group">

                               <g:include controller="alarm" action="index" />

                            </div>
                            <!-- /.list-group -->

                        </div>



                </div>
                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->

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
