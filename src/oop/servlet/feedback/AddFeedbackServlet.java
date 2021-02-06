package oop.servlet.feedback;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Customer;
import oop.classes.Feedback;
import oop.services.CustomerIMPL;
import oop.services.FeedbackIMPL;
import oop.services.ICustomer;
import oop.services.IFeedback;

/**
 * Servlet implementation class FeedbackServlet
 */
public class AddFeedbackServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddFeedbackServlet() {
        super();
    }

	/**
	 * This method called by MainPage.jsp by Make a feedback button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer = iCustomer.getCustomerByID(customerID);
		
		request.setAttribute("customer", customer);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddFeedback.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by AddFeedback.jsp by Submit button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		IFeedback iFeedback = new FeedbackIMPL();
		Feedback feedback = new Feedback();
		
		String message = request.getParameter("message");
		String customerID = request.getParameter("customerID");
		
		if(message.isEmpty()){
			String error = "Message cannot be empty";
			
			ICustomer iCustomer = new CustomerIMPL();
			Customer customer = new Customer();
			
			customer = iCustomer.getCustomerByID(customerID);
			
			request.setAttribute("customer", customer);
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddFeedback.jsp");
			dispatcher.forward(request, response);
			
		} else{
			//Set parameters to the object parameters
			feedback.setCustomerID(request.getParameter("customerID"));
			feedback.setDate(request.getParameter("date"));
			feedback.setMessage(request.getParameter("message"));
			
			iFeedback.addFeedback(feedback);
			
			request.setAttribute("customerID", customerID);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListFeedbacks.jsp");
			dispatcher.forward(request, response);
		}
	}

}
