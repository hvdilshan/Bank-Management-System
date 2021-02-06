<%@page import="oop.classes.Customer"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="/WEB-INF/views/Header.jsp"></jsp:include>

<title>Login to Banking System</title>
</head>
<body class = "body">
	<div class="cusList" >
		<%
			String error = (String) request.getAttribute("error");
			Customer customer = (Customer) request.getAttribute("customer");
			
			if(customer != null){
		%>
		<h3>Your user ID is <%= customer.getCustomer_ID() %></h3>
		
		<% } %>
		<form action="LoginServlet" method="POST">
			<table>
				<caption class = "login">Login</caption>
				
				<tr> 
					<td>User ID </td>
					<td><input type = "text" name = "customerID"></td>
				</tr>
				<tr>
					<td>Password </td>
					<td><input type = "password" name = "password"></td>
				</tr><br/>
				<%if(error != null){ %>
				<tr>
					<td class="error"><%= error %></td>
				</tr><br/>
				<% } %>
				<tr>
					<td><input type = "submit" value = "Login"></td>
				</tr>
			
			</table>
		
		</form>
		<form action = "LinkAddCustomerServlet" method="POST" >
			<button >Register</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>
</body>
</html>