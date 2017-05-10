<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Untitled Document</title>
<link href="userLogin.css" rel="stylesheet" type="text/css">
<link href="CSS Test/userLogin.css" rel="stylesheet" type="text/css">
</head>

<spring:url value="/resources/userLogin.css" var="userLoginCSS" />
<script>
	src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js" >
</script>
<link href="${userLoginCSS}" rel="stylesheet" />
<title>User Login</title>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	$(document)
			.ready(
					function() {

						$("#box2").hide();
						$("#box3").hide();
						$("#box4").hide();
						$("#addEdu").hide();

						$("#create").click(function() {
							$("#box2").show();
							$("#box3").hide()
							$("#box4").hide();

						});
						$("#search").click(function() {
							$("#box2").hide();
							$("#box3").show();
							$("#box4").hide();
							$("#table").hide();

						});
						$("#addNewEducation").click(function() {
							$("#addEdu2").show();
							$("#addEdu").show();
						});
						$("#addEdu2").click(function() {

						});
						/* $("#form1").submit(
								function(e) {
									$.post('addeducation.htm', $('#form1')
											.serialize());
									e.preventDefault();
									$("#addEdu2").trigger('click');

								});
						$("#form2").submit(
								function(e) {
									$.post('addeducation.htm', $('#form2')
											.serialize());
									e.preventDefault();
									$("#addEdu2").trigger('click');

								}); */

						$("#addeducation").click(function() {
							$("#box3").show();
							$("#addEdu2").show();
						});

						$("#updateProfile").click(function() {
							$("#box4").show();
							$("#box3").hide();
							$("#box2").hide();

						});

						$("#addEdu").click(function() {

							$.ajax({
								url : "addeducationcheck.htm",
								success : function(result) {

									//$("#msg").html(result);
									$("#msg").html(result);
								}
							});

						});

						$("#searchJob")
								.click(
										function() {

											$("#table").show();
											var trHTML = '';
											

											$
													.ajax({

														url : "searchjob.htm?jobName="
																+ $("#jobName")
																		.val()
																+ "&jobCategory="
																+ $(
																		"#jobCategory")
																		.val(),
														data : {
															get_param : 'value'
														},
														dataType : 'json',
														success : function(data) {
															

															$
																	.each(
																			data.results,
																			function(
																					index,
																					element) {
																				/* alert(element.jobName); */
																				trHTML += '<tr><td>'
																						+ element.jobName
																						+ '</td><td class=jobId>'
																						+ element.jobID
																						+ '</td>'
																						+ '<td>'
																						+ element.employerName
																						+ '</td>'
																						+ '<td><input type="button" value="Apply" class="apply"></td>'
																						+ '</tr>';

																				/*  $('#Trial').append($('<li><a href="/topicQuestion.htm&topic='+element.topicName+'">'+element.topicName+'</a>', {
																				     text: element.topicName */
																				/* })); */
																				$("#msg").empty().append(msg);
																			});

															$('#table').html(
																	trHTML);

														}
													});
										});
						$("#table").on(
								'click',
								'.apply',
								function() {
									alert("inside apply")
									var dsbutton = $(this);
									var $row = dsbutton.closest("tr");
									var jobId = $row.children('td.jobId')
											.text();
									var personId = '${person}';
									var data = {
										'jobId' : jobId,
										'personId' : personId
									};
									var parentTr = $(this).parent().parent();
									alert(parentTr);
									jQuery.ajax({
										type : "POST",
										dataType : "html",
										url : "applyjob.htm",
										data : data,
										success : function(msg) {
											//if(msg === "success"){
											//var row = document.getElementById(trophyId);
											//row.parentNode.removeChild(row);

											parentTr.remove();
											/* $(".alerttrophy").show(); */
											$("#result").empty().append(msg);
											//}else{
											// alert("Deleting doctor failed");
											//}
										},
										error : function(XMLHttpRequest,
												textStatus, errorThrown) {
											alert("unable to apply to job");
										}
									});
								});

					});
</script>

<body id="body">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:700,600"
		rel="stylesheet" type="text/css">

	<a href="logout.htm" method="get" class="btn"> Log out </a>
	<div class="row">
		<h1>Welcome User ${person.firstName}</h1>
	</div>

	<div class="row">
		<c:set var="person" value="${person}" scope="session" />

		<a href="#"><div class="btn" id="create">Create Profile</div></a> <a
			href="#"><div class="btn" id="search">Search</div></a> <a href="#"><div
				class="btn" id="updateProfile">Update Profile</div></a>
	</div>


	<div class="container">



		<div class="box2" id="box2">
			<div class="row">

				<button type="button" class="btn btn-info" data-toggle="collapse"
					data-target="#demo" id="addNewEducation">Add Education</button>
				<button type="button" class="btn btn-info" data-toggle="collapse"
					data-target="#demo1">Add Experience</button>
			</div>


			<c:set var="msg" value="${msg}" scope="session" />
			<div class="row">
				<div id="demo" class="collapse row">
					<div class="row">
						<h6>
							<div id="msg" style="color: red">${sessionScope.msg}</div>
						</h6>
					</div>
					<!--  Section -->
					<!-- To add Education Information for the user -->
					<form id="form1" method="post" action="addeducation.htm"
						commandName="education">
						<div class="col-md-9">
							<input type="text" name="collegeName" placeholder="College name"
								required="required" class="text" /> <input type="text"
								name="degreeLevel" placeholder="Major" required="required"
								class="text" />

						</div>
						<div class="col-md-3">
							<a href="#" class="btn btn-info" data-toggle="collapse"
								data-target="#demo2" id="addEdu2">Add Education</a>
						</div>
						<div>
							<input type="submit" value="Submit" class="btn btn-info"
								id="addEdu">
						</div>
					</form>

				</div>



				<!-- <a href="#" class="btn btn-info"
								data-toggle="collapse" data-target="#demo2" id="addEdu2">Add
								Another</a> -->




			</div>
			<!-- End of adding of new Education -->


			<!-- Start of adding of new Experience -->

			<form method="post" action="addexperience.htm">
				<div id="demo1" class="collapse row">

					<div class="col-md-9">

						<input type="text" name="companyName" placeholder="Company Name"
							required="required" class="text" required="required" /> <input
							type="text" name="designation" placeholder="Designation"
							required="required" class="text" />

					</div>

					<div>
						<input type="submit" value="Submit" class="btn btn-info">
					</div>

				</div>
			</form>
		</div>
	</div>


	<!-- Start of Searching for Job -->



	<div class="box3" id="box3">
		<!-- <form action="searchjob.htm" method="POST">
 -->

		<h2>Search Job</h2>

		<div class="col-md-12">
			<input type="text" name="jobName" placeholder="Position" class="text"
				id="jobName" required="required" id="jobName" /> <input type="text"
				name="jobCategory" placeholder="Job Category" class="text"
				id="jobCategory" id="jobCategory" />



			<div>
				<input type="button" div class="btn" value="Search Job"
					id="searchJob">
				<div class="row"></div>
				<div>
					<div class="col-md-12">
						<div class="row">
							<table id="table" class="table table-striped">
								<thead>
									<tr>
										<th>Job Name</th>
										<th>JobID</th>
										<th>Employer</th>
										<th>Apply</th>
									</tr>
								</thead>
							</table>
							<div id="emptymessage" style="color: red">${requestScope.errorMsg}</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>


	<!-- </form> -->
	<div class="box4" id="box4">
		<form method="post" action="updateProfile.htm">
			<div class="row">
				<div class="col-md-4">
					<div class="updateForm">
						<input type="text" name="firstName" placeholder="FirstName"
							required="required" class="text" /> <input type="text"
							name="lastName" placeholder="LastName" class="text" /> <input
							type="email" name="email" placeholder="Email" class="text"
							id="emailid" /> <input type="password" name="password"
							placeholder="password" class="text" id="password" />

					</div>
					<div>
						<input type="submit" div class="btn" value="Update"
							id="updateProfileButton">
						<c:set var="person" value="${person}" scope="session" />
					</div>
				</div>
			</div>
		</form>
	</div>





</body>
</html>

