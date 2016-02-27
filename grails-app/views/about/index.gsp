<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Honeyalarm - Bootstrap Admin Theme</title>

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

</head>

<body>

<div id="wrapper">

    <!-- Navigation -->
    <g:include controller="navigationGenerator" action="index"></g:include>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">About</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>


        <ul class="list-group">
            <li class="list-group-item list-group-item-success">Uses great SB-Admin 2 theme (see http://ironsummitmedia.github.io/startbootstrap-sb-admin-2/pages/index.html)</li>
            <li class="list-group-item list-group-item-info">Grails framework</li>
            <li class="list-group-item list-group-item-warning">Mysql</li>
            <li class="list-group-item list-group-item-danger">AngularJS</li>
            <li class="list-group-item list-group-item-danger">Telegram (Bot API)</li>
        </ul>

        <br>

        Coding by Markus "Flake" Schmall using great IntelliJ Idea<br>

        <br>
        Thanks for various discussions / input to Markus Schroer, Andre Vorbach, Rainer Schmidt and Marco Ochse.

        <br><br>

        <a href="/" class="btn btn-primary" role="button">Main</a>

        <a href="http://www.sicherheitstacho.eu" class="btn btn-info" role="button">Go to Sicherheitstacho.eu</a>


    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

<g:include controller="javascriptGenerator" action="index"></g:include>


</body>

</html>
