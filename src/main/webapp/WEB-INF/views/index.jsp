<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<spring:url value="/resources/index.css" var="indexCSS" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${indexCSS}" rel="stylesheet" />

<title>JobHunter</title>
</head>
<body>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:700,600'
		rel='stylesheet' type='text/css'>

	<form method="post" action="login.htm">
		<div class="box">
			<h1>Jobhunter</h1>
			<h5>
				<div id="msg" style="color: red">${requestScope.errormsg}</div>
			</h5>

			<input type="text" name="userName" placeholder="Username"
				required="required" class="username" /> <input type="password"
				name="password" placeholder="Password" required="required"
				class="password" />
			<!--  a href="#"><div class="btn" id="signin">Sign In</div></a>-->
			<input type="submit" value="Sign In" class=btn>
			<!-- End Btn -->

			<a href="createapplicant.htm"><div id="btn2">Sign Up</div></a>
			<!-- End Btn2 -->
			<div>
				<a href="createemployer.htm"><div id="btn3">Register
						Employer</div></a>
			</div>

		</div>
		<!-- End Box -->

	</form>
	<p>
		Forgot your password? <u style="color: #f1c40f;">Click Here!</u>
	</p>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document).ready(
				function() {
					$("#signin").click(
							function() {
								$.ajax({
									url : "login.htm?checklogin="
											+ $("#userName").val()
											+ $("password").val(),
									success : function(result) {
										$("#msg").html(result);
									}
								});

							});
				});
	</script>



</body>
</html>