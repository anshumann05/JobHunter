<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/registerEmployer.css"
	var="registerEmployerCSS" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${registerEmployerCSS}" rel="stylesheet" />

<title>JobHunter</title>
</head>
<body>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:700,600'
		rel='stylesheet' type='text/css'>

	<form method="post" action="createemployer.htm" commandname="employer">
		<div class="box">
			<h1>Register</h1>

			<input type="text" name="employerName" placeholder="Name"
				required="required" class="username" /> <input type="text"
				name="employerCategory" placeholder="Category" required="required"
				class="username" /> <input type="text" name="userName"
				placeholder="Username" required="required" class="username" /> <input
				type="password" name="password" placeholder="password"
				required="required" class="username" />
			<!-- <a href="loginuser.htm"><div
					class="btn">Register</div></a> -->

			<input type=submit value=Register class="btn">



			<!-- End Btn -->



		</div>
		<!-- End Box -->

	</form>



</body>
</html>