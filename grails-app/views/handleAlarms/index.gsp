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

    <!-- Navigation -->
    <nav class="navbar navbar-default navbar-static-top" role="navigation" style="margin-bottom: 0">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="index.html">HoneyAlarm - Admin Console</a>
        </div>
        <!-- /.navbar-header -->

        <ul class="nav navbar-top-links navbar-right">

            <!-- /.dropdown -->
            <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                </a>
                <ul class="dropdown-menu dropdown-user">
                    <li><a href="#"><i class="fa fa-user fa-fw"></i> User Profile</a>
                    </li>
                    <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                    </li>
                    <li class="divider"></li>
                    <li><a href="login.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                    </li>
                </ul>
                <!-- /.dropdown-user -->
            </li>
            <!-- /.dropdown -->
        </ul>
        <!-- /.navbar-top-links -->

        <div class="navbar-default sidebar" role="navigation">
            <div class="sidebar-nav navbar-collapse">
                <ul class="nav" id="side-menu">
                    <li class="sidebar-search">
                        <div class="input-group custom-search-form">

                        </div>
                        <!-- /input-group -->
                    </li>
                    <li>
                        <a href="/honeylalarmg2/ng2"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                    </li>

                    <li>
                        <a href="config.html"><i class="fa fa-wrench fa-fw"></i> Config<span class="fa arrow"></span></a>
                        <!-- /.nav-second-level -->
                    </li>
                    <li>
                        <a href="/honeyalarmg2/about/index"><i class="fa fa-wrench fa-fw"></i> About<span class="fa arrow"></span></a>
                        <!-- /.nav-second-level -->
                    </li>


                </ul>
            </div>
            <!-- /.sidebar-collapse -->
        </div>
        <!-- /.navbar-static-side -->
    </nav>

    <div id="page-wrapper">
        <div class="row">
            <div class="col-lg-12">
                <h1 class="page-header">Alarms</h1>
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
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover" id="dataTables-example">
                            <thead>
                            <tr>
                                <th>Time</th>
                                <th>Source</th>
                                <th>Target</th>
                                <th>Request</th>
                                <th>Action</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr class="odd gradeX">
                                <td>Trident</td>
                                <td>Internet Explorer 4.0</td>
                                <td>Win 95+</td>
                                <td>AttackData</td>

                                <td class="center"><button type="button" class="btn btn-success btn-circle"><i class="fa fa-check center"></i></button><button type="button" class="btn btn-danger btn-circle" data-toggle="tooltip" data-placement="left" title="Tooltip on left"><i class="fa fa-times"></i></button></td>
                            </tr>
                            </tbody>
                        </table>
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

<!-- jQuery -->
<script src="${g.resource(dir: 'js', file:'jquery-2.1.4.min.js')}"></script>
<script src="${g.resource(dir: 'js', file:'bootstrap.js')}"></script>


<!-- Metis Menu Plugin JavaScript -->
<script src="${g.resource(dir: 'js', file:'metisMenu.js')}"></script>

<script src="${g.resource(dir: 'js', file:'raphael-min.js')}"></script>
<script src="${g.resource(dir: 'js', file:'morris.min.js')}"></script>
<script src="${g.resource(dir: 'js', file:'morris-data.js')}"></script>

<!-- Custom Theme JavaScript -->
<script src="${g.resource(dir: 'js', file:'sb-admin-2.js')}"></script>


</body>

</html>
