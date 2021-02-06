<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>

<title>Admin</title>
</head>
<body class = "body">
	<div class = "main">
		<form action = "LinkAddAccountServlet" method = "POST">
			<button class = "transaction-button">Create bank account</button>
		</form>
	
		<form action = "UserProfileServlet" method = "POST">
			<button class = "userProfile-button">Show Profiles</button>
		</form>
		
		<form action = "DeleteFeedbackServlet" method = "GET">
			<button class = "feedback-button">Show Feedbacks</button>
		</form>
		
		<form action = "LogoutServlet" method = "POST">
			<button class = "logout-button">Log Out</button>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>