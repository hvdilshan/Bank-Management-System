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
<title>Add Feedback</title>
</head>
<body class="body">
	<div class="cusList">
		<%
			Customer customer = (Customer) request.getAttribute("customer");
			String error = (String) request.getAttribute("error");
		%>
		<form action=" AddFeedbackServlet" method="POST">
			<table>
				<caption><h2>Add your feedback here</h2></caption>
				
				<tr>
					<td>Customer ID </td>
					<% if(customer != null) { %>
						<td> <input type = "text" value = "<%= customer.getCustomer_ID() %>" disabled="disabled" name = "customerID"></td>
					<% } else{ %>
						<td> <input type = "text" value = "C" name = "customerID"></td>
					<% } %>
				</tr>	
				<tr>
					<td>Date</td>
					<td> <input type = "text" value="DD/MM/YYYY" name="date"></td>
				</tr>	
				<tr>
					<td>Message </td>
					<td><textarea rows="5" cols="40" name = "message"></textarea></td>
				</tr>
				<%if(error != null){ %>
					<tr>
						<td class="error"><%= error %></td>
					</tr><br/>
				<% } %>	
				
					<tr>
						<td><input type = "hidden" name = "customerID" value = "<%= customer.getCustomer_ID() %>"></td>
						<td> <input type = "submit" value="Submit"></td>
					</tr>
					<tr>
						<td> <button type = "reset" value=" reset">Reset</button></td>
					</tr>	
			</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>