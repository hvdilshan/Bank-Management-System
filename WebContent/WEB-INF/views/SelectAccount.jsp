<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>
<title>Select account</title>
</head>
<body class = "body">
	<div class="cusList">
		<%
			String error = (String) request.getAttribute("error");
		%>
		<form action="GetAccountServlet" method="POST">
		<table>
			<tr>
				<td>Input your account number </td>
				<td><input type="text" name = "accountID">
			</tr>
			<%if(error != null){ %>
				<tr>
					<td class="error"><%= error %></td>
				</tr><br/>
				<% } %>			
				<tr>
					<td><button>Submit</button></td>
				</tr>
		</table>
		</form>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>

</html>