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

    <script src="${g.resource(dir: 'js', file:'jquery-2.1.4.min.js')}"></script>
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



</head>


<body>

<g:if test="${alertText}">

    <script>

        bootbox.alert("${alertText}");

    </script>

</g:if>

<div id="wrapper">

    <g:include controller="navigationGenerator" action="index"></g:include>

    <div id="page-wrapper">
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
                <div class="panel panel-default">
                    <div class="panel-heading">
                        <i class="fa fa-bell fa-fw"></i> Notifications Panel
                    </div>
                    <!-- /.panel-heading -->
                    <div >
                        <table class="table table-striped table-bordered" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>Time</th>
                                <th>Source</th>
                                <th>Type</th>
                                <th>Request</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>


                            <g:each in="${reports}" var="alarm">

                                <tr>

                                    <td>${alarm.time}</td>
                                    <td>${alarm.attacker}</td>
                                    <td>${alarm.type}</td>
                                    <td>${alarm.request}</td>

                                    <td class="center"> <a href="/Report/show/${alarm.id}" class="btn btn-primary">View</a> <a href="/Report/confirm/${alarm.id}" class="btn btn-success">Confirm</a> <a href="/Report/delete/${alarm.id}" class="btn btn-danger">Delete</a> <a href="/Report/ignore/${alarm.id}" class="btn btn-success">Ignore</a>  </td>
                                </tr>

                            </g:each>

                            </tbody>
                        </table>
                    </div>

                    <!-- /.panel-body -->
                </div>
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
