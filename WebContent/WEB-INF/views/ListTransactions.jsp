<%@ page import="java.util.ArrayList" %>
<%@ page import="oop.services.ITransaction" %>
<%@ page import="oop.services.TransactionIMPL" %>
<%@ page import="oop.classes.Transaction" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>

<title>Transactions</title>
</head>
<body class="body">
	<div class="cusList">
		<table >
			 <caption>
				 <h2>Transactions</h2>
			 </caption>
			 
			 <form action="AddTransactionServlet" method="get">
			 	<input type = "submit" value="Add new Transaction" >
			 </form>
			 
			  <tr>
			  		<th>Pay From</th>
					<th>Pay To</th>
					<th>Amount</th>
	            </tr>
	            <%
				ITransaction iTransaction = new TransactionIMPL();
				ArrayList<Transaction> arrayList = iTransaction.getAllTransactions();
				
				for (Transaction transaction : arrayList){
				
				%>
				 <tr>
				 	<td> <%=transaction.getPayFrom() %> </td>
					<td> <%=transaction.getPayTo() %> </td>
					<td> <%=transaction.getAmount() %> </td>
					
					<td>
					<form method="GET" action="UpdateTransactionServlet">
					
						 <input type="hidden" name="transactionID" value="<%=transaction.getTransactionID() %>"/>
						 <input type="submit" value= "Edit Transaction" class="select-button" /> 
						 
					 </form>
					 
					 <form action="DeleteTransactionServlet" method="POST">
					 
					 	<input type ="hidden" name ="transactionID" value="<%=transaction.getTransactionID() %>"/>
					 	<input type = "submit" value="Delete Transaction" >
					 	
					 </form>
					 </td>	
					</tr>			
				<%	
				   }
	            %>     
			</table>
		</div>
		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>