<%@ page import="oop.services.CustomerIMPL" %>
<%@ page import="oop.services.ICustomer" %>
<%@ page import="oop.classes.Customer" %>
<%@ page import="java.util.ArrayList" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>

<title>Customer list</title>
</head>
<body class="body">
	<div class="cusList">
		<table >
			 <caption>
				 <h1>List of Customers</h1>
			 </caption>
			 <form action="LinkAddCustomerServlet" method="POST">
			 	<input type = "submit" value="Add New Customer" >
			 </form>	
			  <tr>
	                <th>Customer ID</th>
	                <th>Name</th>
	                <th>Mobile</th>
	                <th>NIC</th>
	                <th>Password</th>
	            </tr>
	            <%
	            ICustomer iaccount = new CustomerIMPL();
				ArrayList<Customer> arrayList = iaccount.getAllCustomers();
				
				for(Customer customer : arrayList){
				%>
				 <tr>
					<td> <%=customer.getCustomer_ID() %> </td>
					<td> <%=customer.getCustomer_Name() %> </td>
					<td> <%=customer.getMobile_Number() %> </td>
					<td> <%=customer.getNic_Number() %> </td>
					<td> <%=customer.getPassword()%> </td>
					<td> 
					<form method="POST" action="DeleteCustomerServlet">
						<input type="hidden" name="customerID" value="<%=customer.getCustomer_ID() %>"/>
						 <input type="submit" value= "Delete" class="select-button" /> 
					 </form>
					 
					<form method="GET" action="UserProfileServlet">
						<input type="hidden" name="customerID" value="<%=customer.getCustomer_ID() %>"/>
						 <input type="submit" value= "Edit" class="select-button" /> 
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