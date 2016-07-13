<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

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

<div class="container">
	<div class="row">
		<div class="col-md-4 col-md-offset-4">
			<div class="login-panel panel panel-default">
				<div class="panel-heading">
					<h3 class="panel-title">Please Sign In</h3>
				</div>
				<div class="panel-body">
					<form action='${postUrl}' method='POST' id='loginForm' role='form' autocomplete='off'>
						<table>
							<tr>
								<th>

								</th>
								<th>

							</th>

							</tr>


							<tr>
								<td>EMail</td>
								<td> <input type='text' class='text_' name='j_username' id='username' /></td>
							</tr>

							<tr>
								<td>Password</td>
								<td> <input type='password' class='text_' name='j_password' id='password' /></td>
							</tr>

							<tr class="blank_row">
								<td colspan="3"></td>
							</tr>

						</table>

						<!-- Change this to a button or input when using this as a form -->
						<input type='submit' class="btn btn-primary" value='Login' />


					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- jQuery -->
<script src="${g.resource(dir: 'js', file:'jquery.min.js')}"></script>
<script src="${g.resource(dir: 'js', file:'bootstrap.min.js')}"></script>



</body>

</html>


