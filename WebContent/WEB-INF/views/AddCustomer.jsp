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

<title>Register</title>
</head>
<body class = "body">
	<div class = "addCustomer">
		<%
			Customer customer = (Customer) request.getAttribute("customer");
			String error = (String) request.getAttribute("error");
		%>
		<form method="POST" action="AddCustomerServlet">
			<table class = "add_cus_table">
				<caption><h2>Register to Online Banking System</h2></caption>
				<tr>
					<td>Customer Name</td>
					<td><input type="text" name="customerName" /></td>
				</tr>
				<tr>
					<td>NIC</td>
					<td><input type="text" name="nic" /></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="text" name="mobile" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Re-enter password</td>
					<td><input type="password" name="rePassword" /></td>
				</tr>
				
				<tr>
					<td ><input type="submit" value="Create Account" class="add_cus_button" /> </td>
				</tr>
				<tr>	
					<td ><input type="reset" value="Reset" class="reset_cus_button" /></td>
				</tr>
				
				<%if(error != null){ %>
				<tr>
					<td class="error"><%= error %></td>
				</tr><br/>
				<% } %>
			</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>