<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="css/bootstrap.min.css" rel="stylesheet">
<script src="js/bootstrap.min.js"></script> 
<script src="js/jquery.js"></script>
<title>Ultimate Solutions</title>
<style>
.col-md-offset-3{

margin-top:15%;
}
</style>
</head>
<body>


<div class="container">
<div class="center-block text-center">
<div class="col-md-6 col-md-offset-3 form-horizontal"><!--login form-->
<h2>Login to your Account</h2>
<form  action="loginServlet" method="post">
<input class="form-control" type="email" name="un" required="required" placeholder="Email Address" /><br/>
<input class="form-control" type="password" name="pw" required="required" placeholder="Password"  /><br/>
<input class="form-control btn btn-primary" type="submit" value="Login" >
</form>
<% if(request.getAttribute("message") == "Invalid Credentials") 
	   { %>
	<div class="text-center text-danger"> <h4>Log in failed !</h4></div>
	<% } %>
</div><!--/login form-->
</div>
	
	
</div>

</body>
</html>