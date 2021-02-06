<%@page import="java.util.ArrayList"%>
<%@page import="oop.services.FeedbackIMPL"%>
<%@page import="oop.services.IFeedback"%>
<%@page import="oop.classes.Feedback"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link href = "Home.css" rel = "stylesheet">
<link href = "views.css" rel = "stylesheet">

<jsp:include page="Header.jsp"></jsp:include>
<title>Feedbacks</title>
</head>
<body class="body">

	<div class="cusList">
		<table >
		 <caption>
			 <h2>List of feedbacks</h2>
		 </caption>
		 
		 <form action="AddFeedbackServlet" method="get">
		 	<input type = "submit" value="Add New Feedback" >
		 </form>				 
		  <tr>
		  		<th>Feedback ID</th>
                <th>Customer ID</th>
                <th>Date</th>
                <th>Message</th>
            </tr>
            <%
            String CustomerID = (String) request.getAttribute("customerID");
            
            IFeedback iFeedback = new FeedbackIMPL();
			ArrayList<Feedback> arrayList = iFeedback.getFeedbackByCustomer(CustomerID);
			
			for(Feedback feedback : arrayList){
			%>
			 <tr>
			 	<td> <%=feedback.getFeedbackID()%> </td>
				<td> <%=feedback.getCustomerID()%> </td>
				<td> <%=feedback.getDate() %> </td>
				<td> <%=feedback.getMessage() %> </td>
				
				<td> 
				<form method="get" action="UpdateFeedbackServlet">
				
					 <input type="hidden" name="feedbackID" value="<%=feedback.getFeedbackID() %>"/>
					 <input type="submit" value= "Edit Feedback" /> 
					 
				 </form>
				 
				 
				 <form action="DeleteFeedbackServlet" method="POST">
				 	<input type = "hidden" name = "feedbackID" value="<%=feedback.getFeedbackID() %>">
				 	<input type = "submit" value="Delete Feedback" >
				 </form>
				 </td>	
				</tr>			
			<%	
			   }
            %>     
				 <form method="POST" action="MainPageServlet">
					<input type="hidden" name="customerID" value="<%=CustomerID %>"/>
			 		<input type="submit" value= "Main Page" class="select-button" /> 
				 </form>
		</table>
	</div>
	<jsp:include page="Footer.jsp"></jsp:include>
</body>
</html>