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
<title>Profile</title>
</head>
<body class = "body">
	
	<%
		Customer customer = (Customer) request.getAttribute("customer");

	%>
	<div class="cusList">
		<table>
			<caption><h3><%= customer.getCustomer_Name() %>'s profile</h3></caption>
			
			<form method="POST" action="MainPageServlet">
						<input type="hidden" name="customerID" value="<%=customer.getCustomer_ID() %>"/>
						 <input type="submit" value= "Main Page" class="select-button" /> 
			 </form>
			<tr>
				<td>customer_ID</td>
				<td><input type="text" name="customerID" disabled="disabled" value= "<%=customer.getCustomer_ID() %>"/></td>
			</tr>

			<tr>
				<td>Customer Name</td>
				<td><input type="text" name="customerName" disabled="disabled" value="<%= customer.getCustomer_Name() %>"/></td>
			</tr>
			<tr>
				<td>Mobile</td>
				<td><input type="text" name="mobile" disabled="disabled" value="<%= customer.getMobile_Number() %>"/></td>
			</tr>
			<tr>
				<td>NIC</td>
				<td><input type="text" name="nic" disabled="disabled" value="<%=customer.getNic_Number() %>"/></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password" disabled="disabled" value="<%=customer.getPassword() %>"/></td>
			</tr>
			<tr>
				<td> 
					<form method="POST" action="SelectCustomerServlet">
						<input type="hidden" name="customerID" value="<%=customer.getCustomer_ID() %>"/>
						 <input type="submit" value= "Edit" class="select-button" /> 
					 </form>
				</td>	
			</tr>
				
		</table>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>