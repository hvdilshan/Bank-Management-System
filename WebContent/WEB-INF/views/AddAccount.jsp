<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>

<title>Create account</title>
</head>
<body class = "body">
	<div class="cusList">
		<%
			String error = (String) request.getAttribute("error");
		%>
		<form method="POST" action="AddAccountServlet">
			<table>
			
				<tr>
					<td>customer ID</td>
					<td><input type="text" name="customerID" /></td>
				</tr>
				<tr>
					<td>Account Type</td>
					<td><input type="text" name="accountType" /></td>
				</tr>
				<tr>
					<td>Account Balance</td>
					<td><input type="text" name="accountBalance" /></td>
				</tr>
				<tr>
					<td>Create Date</td>
					<td><input type="text" name="createDate" /></td>
				</tr>
				<%if(error != null){ %>
					<tr>
						<td class="error"><%= error %></td>
					</tr><br/>
				<% } %>
				
				<tr>
					<td ><input type="submit" value="Add Acount" class="add-button" /> </td>
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