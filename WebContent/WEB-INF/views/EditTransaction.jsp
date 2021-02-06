<%@page import="oop.classes.Transaction"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>
<title>Edit Transaction</title>
</head>
<body class="body">
	<div class="cusList">
		<form method="POST" action="UpdateTransactionServlet">
		
		<% Transaction transaction = (Transaction) request.getAttribute("transaction"); %>
			<table>
				<caption>Edit Transaction Details</caption>
			
				<tr>
					<td>Transaction ID</td>
					<td><input type="text" name="transactionID" disabled="disabled" value= "<%=transaction.getTransactionID() %>"/></td>
				</tr>
	
				<tr>
					<td>Customer ID</td>
					<td><input type="text" name="customerID" disabled="disabled" value="C00001"/></td>
				</tr>
				<tr>
					<td>PAy From</td>
					<td><input type="text" name="payFrom" value="<%= transaction.getPayFrom() %>"/></td>
				</tr>
				<tr>
					<td>Pay To</td>
					<td><input type="text" name="payTo" value="<%=transaction.getPayTo() %>"/></td>
				</tr>
				
				<tr>
					<td>Amount</td>
					<td><input type="text" name="amount" value="<%=transaction.getAmount()%>"/></td>
				</tr>
				
				<tr>
					<td ><input type="hidden" name = "transactionID" value = "<%=transaction.getTransactionID() %>"/>
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