<%@ page import="oop.services.IAccount" %>
<%@ page import="oop.services.AccountIMPL" %>
<%@ page import="oop.classes.Account" %>
<%@ page import="java.util.ArrayList" %>
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

<title>Accounts</title>
</head>
<body class="body">
	<div class="cusList">
		<table >
			 <caption>
				 <h2>List of Accounts</h2>
			 </caption>
			 <form action="LinkAddAccountServlet" method="POST">
			 	<input type = "submit" value="Add New Account" >
			 </form>
			  <tr>
			  		<th>Customer ID</th>
	                <th>Account NO</th>
	                <th>Account Type</th>
	                <th>Create Date</th>
	                <th>Balance</th>
	            </tr>
	            <%
	            IAccount iaccount = new AccountIMPL();
				ArrayList<Account> arrayList = iaccount.getAllAccounts();
				
				for(Account account : arrayList){
				%>
				 <tr>
				 	<td> <%=account.getCustomerID() %> </td>
					<td> <%=account.getAccount_NO() %> </td>
					<td> <%=account.getAccount_Type() %> </td>
					<td> <%=account.getCreate_Date() %> </td>
					<td> <%=account.getAccount_Balance() %></td>
					
					<td> 
					<form method="POST" action="SelectAccountServlet">
					
						 <input type="hidden" name="accountID" value="<%=account.getAccount_NO() %>"/>
						 <input type="submit" value= "Edit Account" class="select-button" /> 
						 
					 </form>
					 
					 <form action="DeleteAccountServlet" method="POST">
					 
					 	<input type = "hidden" name = "accountID" value = "<%=account.getAccount_NO() %>">
					 	<input type = "submit" value="Delete Account" >
					 	
					 </form>
					 </td>	
					</tr>			
				<%	
				   }
	            %>     
			</table>
			<form action = "AdminPanelServlet" method="POST" >
				<button >Main Panel</button>
			</form>  
		</div>
		<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>