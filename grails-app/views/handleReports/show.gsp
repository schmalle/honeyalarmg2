<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en" ng-app="honeyalarm">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Simple honeypot UI">
    <meta name="author" content="Markus Schmall">

    <title>Honeyalarm - Admin console</title>




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
    <script src="${g.resource(dir: 'js', file:'jquery.min.js')}"></script>
    <script src="${g.resource(dir: 'js', file:'bootstrap.min.js')}"></script>

    <script src="${g.resource(dir: 'js', file:'bootbox.min.js')}"></script>



</head>


<body>




<div id="wrapper">


    <g:include controller="navigationGenerator" action="index"></g:include>

    <div id="page-wrapper">


    <g:if test="${alertText}">

        <script language="JavaScript">

            bootbox.alert("${alertText}");

        </script>

    </g:if>

        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Reports</h1>
            </div>
            <!-- /.col-lg-12 -->
        </div>
        <!-- /.row -->

        <!-- /.row -->
        <div class="row">

            <!-- /.col-lg-8 -->
            <div>
                <!-- /.panel -->

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
