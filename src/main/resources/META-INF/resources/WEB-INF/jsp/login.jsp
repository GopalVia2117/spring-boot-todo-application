<html>
	<head>
		<title>Login Page</title>
		<link href="webjars/bootstrap/5.3.2/css/bootstrap.min.css"  rel="stylesheet"/>
	</head>
	<body>
		<div class="container">
			<h1>Login Page</h1>
			<p style="color: red">${error}</p>
			<form class="form" method="post" action="/login">
				Name: <input class="form-control" type="text" name="username" />
				Password: <input class="form-control" type="password" name="password"/>
				<input class="form-control btn btn-primary mt-2" type="submit"/>
			</form>
		</div>
		<script src="webjars/bootstrap/5.3.2/js/bootstrap.min.js" ></script>
		<script src="webjars/jquery/3.6.0/jquery.min.js"></script>
	</body>
</html>