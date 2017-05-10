<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<spring:url value="/resources/registerApplicant.css" var="registerCSS" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link href="${registerCSS}" rel="stylesheet" />

<title>JobHunter</title>
</head>
<body>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:700,600'
		rel='stylesheet' type='text/css'>

	<form method="post" action="createapplicant.htm" commandname="person">
		<div class="box">
			<h1>Register</h1>
			<h6>
				<div id="msg" style="color: red">${requestScope.errormsg}</div>
			</h6>

			<input type="text" name="firstName" placeholder="FirstName"
				required="required" class="text" /> <input type="text"
				name="lastName" placeholder="LastName" required="required"
				class="text" /> <input type="email" name="email"
				placeholder="Email" required="required" class="email" id="emailid" />

			<input type="text" name="userName" placeholder="Username"
				required="required" class="email" id="userName" /> <input type="password"
				name="password" placeholder="password" required="required"
				class="email" />
			<!-- <a href="loginuser.htm"><div
					class="btn">Register</div></a> -->

			<input type=submit value=Register class="btn">



			<!-- End Btn -->



		</div>
		<!-- End Box -->

	</form>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	<script>
		$(document).ready(function() {
			$("#emailid").blur(function() {
				//alert("heloo..");
				$.ajax({
					url : "checkEmailId.htm?emailid=" + $("#emailid").val(),
					success : function(result) {

						//$("#msg").html(result);
						$("#msg").html(result);
						if (result != '') {
							$("#emailid").css('border-color', 'red');
						} else {
							//alert("success");
							$("#emailid").css('border-color', '#ccc');
						}

						//$("#emailid").text("");

					}

				});
			});
			
			$("#userName").blur(function() {
				//alert("heloo..");
				$.ajax({
					url : "checkUsername.htm?userName=" + $("#userName").val(),
					success : function(result) {
						//$("#msg").html(result);
						$("#msg").html(result);
						if (result != '') {
							$("#username").css('border-color', 'red');
							//$("#username").text("");
						}
						else {
							//alert("success");
							$("#username").css('border-color', '#ccc');
						}

					}
				});
			});

		});
		
	</script>

</body>
</html>