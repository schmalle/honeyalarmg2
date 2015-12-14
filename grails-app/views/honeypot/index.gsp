<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<%@ page import="java.util.Date" %>

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Honeyalarm - Honeypot overview</title>

    <!-- Bootstrap Core CSS -->
    <link href="${g.resource(dir: 'css', file:'bootstrap.min.css')}" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${g.resource(dir: 'css', file:'metisMenu.css')}" rel="stylesheet">


    <!-- Timeline CSS -->
    <link href="${g.resource(dir: 'css', file:'timeline.css')}" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${g.resource(dir: 'css', file:'sb-admin-2.css')}" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${g.resource(dir: 'css', file:'morris.css')}" rel="stylesheet">

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

    <g:include controller="navigationGenerator" action="index"></g:include>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Honeypots</h1>
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
                        <i class="fa fa-bell fa-fw"></i> Honeypots
                    </div>
                    <!-- /.panel-heading -->
                    <div >
                        <table class="table table-striped table-bordered" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>Name</th>
                                <th>IP</th>
                                <th>Added</th>
                                <th>Last seen</th>
                                <th>Action</th>


                            </tr>
                            </thead>
                            <tbody>

                            <g:each in="${Honeypots}" var="honeypot">

                            <tr>
                                <td>${honeypot.name}</td>
                                <td>${honeypot.ip}</td>
                                <td>${honeypot.added}</td>
                                <td>${honeypot.lastseen}</td>

                                <td class="center">   <a href="/Honeypot/delete/${honeypot.id}" class="btn btn-danger">Delete</a>   </td>
                            </tr>
                                </g:each>
                            </tbody>
                        </table>
                    </div>

                    <!-- /.panel-body -->
                </div>
                <!-- /.panel -->


                <a href="/" class="btn btn-primary" role="button">Main</a>

                <br><br>

        <div class="row">
            <div class="col-lg-12">
                <h2 class="page-header">Add new honeypot</h2>
            </div>
            <!-- /.col-lg-12 -->
        </div>

        <div >
            <form action="/saveHoneypot" method="post">

                <g:hiddenField name="added" value="${dateAdded}"/>
            <table class="table table-striped table-bordered" id="add">
                <thead>
                <tr>
                    <th>Name</th>
                    <th>Password</th>



                </tr>
                </thead>
                <tbody>


                    <tr>
                        <td><g:textField name="name" value=""/></td>
                        <td><g:textField name="password" value=""/></td>
                    </tr>
                </tbody>
            </table>

                <input type="submit" class="btn btn-danger" value="Submit">
            </form>
        </div>







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
