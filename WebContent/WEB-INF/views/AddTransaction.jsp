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
<title>Money Transfer</title>
</head>
<body class="body">
	<div class="cusList">
		<%
			Account account = (Account) request.getAttribute("account");
			String error = (String) request.getAttribute("error");
		%>
		<form action="AddTransactionServlet" method="POST">
			<Table>
				<caption><h3>Money Transfer</h3></caption>
				<br/>
				<%if(error != null){ %>
					<tr>
						<td class="error"><%= error %> : <%=account.getAccount_Balance() %></td>
					</tr><br/>
				<% } %>
				<tr>
					<td>Account Balance</td>
					<td><Input type = "text" value = "    <%= account.getAccount_Balance() %>" disabled="disabled"></td>
				</tr>
				<tr>
					<td>Pay From</td>
					<td> <input type = "text" name = "payFrom"></td>
				</tr>
				
				<tr>
					<td>Pay To</td>
					<td><input type = "text" name = "payTo"></td>
				</tr>
			
				<tr>
					<td>Amount</td>
					<td><input type = "text" name = "amount"></td>
				</tr>
				
				<tr>
					<td>Mobile Number</td>
					<td><input type = "text" name = "mobile"></td>
				</tr>
				<tr>
					<td><input type = "hidden" name = "accountID" value="<%= account.getAccount_NO() %>">
					<td><input type="submit" value = "Submit" ></td>
				</tr>
			</Table>
			
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>