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
				<h1 class="page-header">Users</h1>
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
						<i class="fa fa-bell fa-fw"></i> Users
					</div>
					<!-- /.panel-heading -->
					<div >
						<table class="table table-striped table-bordered" id="dataTables-example">
							<thead>
							<tr>
								<th>Name</th>
								<th>TwitterName</th>
								<th>Role</th>
								<th>Action</th>
							</tr>
							</thead>
							<tbody>

							<g:each in="${users}" var="user">

								<tr>
									<td>${user.name}</td>
									<td>${user.twitterName}</td>
									<td>${user.role}</td>
									<td class="center">   <a href="/User/delete/${user.id}" class="btn btn-danger">Delete</a>   </td>
								</tr>
							</g:each>
							</tbody>
						</table>
					</div>

					<!-- /.panel-body -->
				</div>
				<!-- /.panel -->


		<div class="col-lg-12">
			<h1 class="page-header">Add new User</h1>
		</div>

				<form method="GET" action="/newUser">


					<table class="table table-striped table-bordered" id="dataTables-example">
						<thead>
						<tr>
							<th>Name</th>
							<th>Password</th>

							<th>TwitterName</th>
							<th>Role</th>
						</tr>
						</thead>
						<tbody>
							<tr>
								<td>
									<input type="text" class="form-control" id="username">
								</td>
								<td>
										<input type="text" class="form-control" id="password">
								</td>
								<td>
										<input type="text" class="form-control" id="twitterName">
								</td>
								<td>
										<input type="text" class="form-control" id="role">
								</td>
							</tr>
						</tbody>
					</table>



					<input type="submit" class="btn btn-info" value="Submit">

				</form>

				<br>

				<a href="/" class="btn btn-primary" role="button">Main</a>

				<br><br>









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
