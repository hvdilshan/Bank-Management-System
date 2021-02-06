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
<title>Edit customer</title>
</head>
<body class="body">
	<div class="cusList">
		<form method="POST" action="UpdateCustomerServlet">
		
		<% Customer customer = (Customer) request.getAttribute("customer"); %>
			<table>
				<caption>Edit Account Details</caption>
			
				<tr>
					<td>customer_ID</td>
					<td><input type="text" name="customerID" disabled="disabled" value= "<%=customer.getCustomer_ID() %>"/></td>
				</tr>
	
				<tr>
					<td>Customer Name</td>
					<td><input type="text" name="customerName" value="<%= customer.getCustomer_Name() %>"/></td>
				</tr>
				<tr>
					<td>Mobile</td>
					<td><input type="text" name="mobile" value="<%= customer.getMobile_Number() %>"/></td>
				</tr>
				<tr>
					<td>NIC</td>
					<td><input type="text" name="nic" value="<%=customer.getNic_Number() %>"/></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="text" name="password" value="<%=customer.getPassword() %>"/></td>
				</tr>
				
				<tr>
					<td ><input type="hidden" name = "customerID" value = "<%=customer.getCustomer_ID() %>"/>
					<input type="submit" value="Update" class="add-button" /> </td>
				</tr>
				<tr>	
					<td ><input type="reset" value="Reset" class="reset-button" /></td>
				</tr>
			</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>