<%@page import="oop.classes.Account"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>
<title>Edit account</title>
</head>
<body class="body">
	<div class="cusList">
		<form method="POST" action="UpdateAccountServlet">
		
		<% Account account = (Account) request.getAttribute("account"); %>
			<table>
				<caption>Edit Account Details</caption>
			
				<tr>
					<td>customer_ID</td>
					<td><input type="text" name="customerID" disabled="disabled" value= "<%=account.getCustomerID() %>"/></td>
				</tr>
				<tr>
					<td>Account No</td>
					<td><input type="text" name="accountNo" disabled="disabled" value= "<%=account.getAccount_NO() %>"/></td>
				</tr>
				<tr>
					<td>Account Type</td>
					<td><input type="text" name="accountType" value="<%= account.getAccount_Type() %>"/></td>
				</tr>
				<tr>
					<td>Account Balance</td>
					<td><input type="text" name="accountBalance" value="<%= account.getAccount_Balance() %>"/></td>
				</tr>
				<tr>
					<td>Create Date</td>
					<td><input type="text" name="createDate" value="<%=account.getCreate_Date() %>"/></td>
				</tr>
				
				<tr>
					<td ><input type="hidden" name = "accountID" value = "<%= account.getAccount_NO() %>"/>
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