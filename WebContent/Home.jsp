<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">

<jsp:include page="/WEB-INF/views/Header.jsp"></jsp:include>

<title>Home</title>
</head>
<body class = "body">
	<div class="home">
		<form action = "LoginServlet" method="GET" >
			<button class = "login-button">Login</button>
		</form>
		
		<form action = "LinkAddCustomerServlet" method="POST" >
			<button class = "register-button">Register</button>
		</form>
	</div>
	<jsp:include page="/WEB-INF/views/Footer.jsp"></jsp:include>
</body>

</html>