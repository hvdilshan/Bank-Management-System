<%@page import="oop.classes.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>
<title>Edit feedback</title>
</head>
<body class="body">
	<%
		Feedback feedback = (Feedback) request.getAttribute( "feedback");
	%>
	<div class="cusList">
		<form action="UpdateFeedbackServlet" method="POST">
			<table>
				<caption><h2>Edit feedback</h2></caption>
				
				<tr>
					<td>Feedback ID</td>
					<td><input type = "text" disabled="disabled" name = "feedbackID" value = "<%= feedback.getFeedbackID() %>" /></td>
				</tr>
				<tr>
					<td>Customer ID</td>
					<td><input type = "text" disabled="disabled" name = "customerID" value = "<%= feedback.getCustomerID() %>" /></td>
				</tr>
				<tr>
					<td>Date</td>
					<td><input type = "text" name = "date" value = "<%= feedback.getDate() %>" /></td>
				</tr>
				<tr>
					<td>Message</td>
					<td><textarea type = "text" name = "message"  ><%= feedback.getMessage() %></textarea></td>
				</tr>
				
				<tr>
					<td><input type = "hidden" name = "feedbackID" value="<%= feedback.getFeedbackID() %>"></td>
					<td><input type ="submit" value="Update"></td>
			</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>