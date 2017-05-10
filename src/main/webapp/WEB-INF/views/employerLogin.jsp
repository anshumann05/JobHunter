<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>Employer Page</title>
</head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script>
	src = "https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"
</script>
<link rel="stylesheet"
	href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">


<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>

<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
<script>
	src = "https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"
</script>
<script>
	$(document)
			.ready(
					function() {
						var jsonGlobal = "";
						$("#box2").hide();
						$("#box3").hide();

						/* $("#updateJob").hide(); */

						$("#create").click(function() {
							$("#box3").show();
							$("box2").hide();
							/* $("#addJob").hide(); */
							$("#table").hide();

						});

						$("#addNewJob").click(function() {

						});
						$("#update")
								.click(
										function() {

											$("#box3").hide();
											$("#box2").show();
											$("#table").show();

											var trHTML = '';

											$
													.ajax({

														url : "updatejob.htm",
														data : {
															get_param : 'value'
														},
														dataType : 'json',
														success : function(data) {
															jsonGlobal = data.results;

															$
																	.each(
																			data.results,
																			function(
																					index,
																					element) {
																				/* alert(element.jobName); */
																				trHTML += '<tr><td>'
																						+ element.jobName
																						+ '</td><td class="jobID">'
																						+ element.jobID
																						+ '</td>'
																						+ '<td><input type="submit" value="Update" disblaed class="updateJob"/></td>'
																						+ '<td><input type="button" value="Delete" class="delete"/></td>'
																						+ '</tr>';

																			});

															$('#table').html(
																	trHTML);

														}

													});

											$("#table")
													.on(
															'click',
															'.delete',
															function() {

																var dsbutton = $(this);
																var $row = dsbutton
																		.closest("tr");
																var jobId = $row
																		.children(
																				'td.jobID')
																		.text();
																var parentTr = $(
																		this)
																		.parent()
																		.parent();

																jQuery
																		.ajax({
																			type : "POST",
																			dataType : "html",
																			url : "deletejob.htm",
																			data : "jobId="
																					+ jobId,
																			success : function(
																					msg) {
																				//if(msg === "success"){
																				//var row = document.getElementById(trophyId);
																				//row.parentNode.removeChild(row);

																				parentTr
																						.remove();

																				$(
																						"#result")
																						.empty()
																						.append(
																								msg);
																				//}else{
																				// alert("Deleting doctor failed");
																				//}
																			},
																			error : function(
																					XMLHttpRequest,
																					textStatus,
																					errorThrown) {
																				alert("error deleting Job");
																			}
																		});

															});
										});

					});
</script>
<style type="text/css">
body {
	font-family: 'Open Sans', sans-serif;
	background: #3498db;
	margin: 0 auto 0 auto;
	width: 100%;
	text-align: center;
	margin: 20px 0px 20px 0px;
}

p {
	font-size: 12px;
	text-decoration: none;
	color: #ffffff;
}

.box {
	position: absolute;
	/* top: 80px;
	left: 120px;
	width: 800px;
	 height: 160px;
	border-radius: 6px;
	margin: 0 auto 0 auto;
	padding: 0px 0px 0px 0px; */
}

.box2 {
	position: absolute;
	top: 180px;
	left: 114px;
	width: 900px;
	height: 120px;
	padding: 0px 0px 0px 0px;
}

.box3 {
	position: absolute;
	top: 180px;
	left: 114px;
	width: 900px;
	height: 120px;
	padding: 0px 0px 0px 0px;
}

.box4 {
	position: absolute;
	top: 180px;
	left: 114px;
	width: 900px;
	height: 120px;
	padding: 0px 0px 0px 0px;
}

.text {
	background: #ecf0f1;
	border: #ccc 1px solid;
	border-bottom: #ccc 2px solid;
	padding: 8px;
	width: 250px;
	color: #AAAAAA;
	margin-top: 10px;
	font-size: 1em;
	border-radius: 4px;
	text-transform: capitalize;
}

.btn {
	background: #2ecc71;
	width: 125px;
	padding-top: 5px;
	padding-bottom: 5px;
	color: white;
	border-radius: 4px;
	border: #27ae60 1px solid;
	margin-top: 20px;
	margin-bottom: 20px;
	float: left;
	margin-left: 76px;
	font-weight: 800;
	font-size: 0.8em;
}

.btn1 {
	background: #ecf0f1;
	width: 125px;
	padding-top: 5px;
	padding-bottom: 5px;
	color: white;
	border-radius: 4px;
	border: #27ae60 1px solid;
	margin-top: 20px;
	margin-bottom: 20px;
	float: left;
	margin-left: 76px;
	font-weight: 800;
	font-size: 0.8em;
}

#footer {
	bottom: 0;
	width: 50%;
	background-color: #afa;
	height: 150px;
	position: fixed;
	text-align: center;
}
</style>
<body id="body">
	<link href="http://fonts.googleapis.com/css?family=Open+Sans:700,600"
		rel="stylesheet" type="text/cs">
	<a href="logout.htm" method="get" class="btn">
		Log out
	</a>



	<h1>Welcome Employer ${employer.employerName}</h1>

	<div class="box">
		<c:set var="employer" value="${employer}" scope="session" />
		<c:set var="job" value="${job}" scope="session" />

		<a href="#"><div button type="button" class="btn btn-info"
				data-toggle="collapse" data-target="#demo" id="create">Add Job</div></a>
		<a href="#"><div class="btn" id="update">Update Job</div></a>
	</div>



	<!-- <button type="button" class="btn btn-info" data-toggle="collapse"
				data-target="#demo" id="addNewJob">Add New Job</button> -->
	</div>


	<div class="row">
		<!-- <div id="demo" class="collapse row"> -->
		<div class="row">
			<h6>
				<div id="result" style="color: red">${requestScope.errormsg}</div>
			</h6>
		</div>
		<!--  Section -->









		<!-- <div class="col-md-3">
						<a href="#" class="btn btn-info" data-toggle="collapse"
							data-target="#demo2" id="addEdu2">Add Job</a>
					</div> -->


	</div>
	</div>
	</form>
	<!-- </div> -->
	</div>
	</div>



	<div class="box2" id="box2">
		<div class="col-md-12">

			<table id="table" class="table table-striped table-hover">
				<thead>
					<tr>
						<th>Job Name</th>
						<th>JobID</th>
						<th>Update</th>
						<th>Delete</th>
					</tr>
				</thead>
			</table>
			<div id="msg" style="color: red">${requestScope.errorMsg}</div>


		</div>
	</div>
	<div class="box3" id="box3">
		<form id="form1" method="post" action="addjob.htm">

			<div class="col-md-8">
				<input type="text" name="jobName" placeholder="Job name"
					required="required" class="text" id="jobName" /> <input
					type="text" name="jobCategory" placeholder="Job Category"
					required="required" class="text" id='jobCategory' />

			</div>


			<div class="col-md-8">
				<input type"textarea" placeholder="Job Description"
					name="jobDescription" class="text" />
			</div>


			<input type="submit" value="Submit" class="btn btn-info" id="addJob">


		</form>
	</div>







</body>
</html>