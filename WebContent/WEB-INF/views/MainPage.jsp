<%@page import="oop.classes.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>

<title>Main Menu</title>
</head>
<body class = "body">
	<div class = "main">
		<%
			Customer customer = (Customer) request.getAttribute("customer");
		%>
		<h2>Welcome <%= customer.getCustomer_Name() %> !</h2>
		
		<form action = "AddTransactionServlet" method = "GET">
			<button class = "transaction-button">Transaction</button>
		</form>
	
		<form action = "SelectCustomerServlet" method = "GET">
			<input type = "hidden" name="customerID" value = "<%= customer.getCustomer_ID() %>" />
			<input type = "submit"  value = "Show Profile" class = "userProfile-button" />
		</form>
		
		<form action = "AddFeedbackServlet" method = "GET">
			<input type = "hidden" name="customerID" value = "<%= customer.getCustomer_ID() %>" />
			<input type = "submit"  value = "Make a feedback" class = "feedback-button" />
	</form>
		
		<form action = "LogoutServlet" method = "POST">
			<button class = "logout-button">Log Out</button>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>