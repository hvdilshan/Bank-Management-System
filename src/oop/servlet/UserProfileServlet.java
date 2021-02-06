package oop.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Customer;
import oop.services.CustomerIMPL;
import oop.services.ICustomer;

/**
 * Servlet implementation class UserProfileServlet
 */

public class UserProfileServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserProfileServlet() {
        super();
    }

	/**
	 * This method called by ListCustomers.jsp by Edit button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer = iCustomer.getCustomerByID(customerID);
		
		request.setAttribute("customer", customer);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Admin_EditCustomerList.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by AdminPanel.jsp by Show profiles button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListCustomers.jsp");
		dispatcher.forward(request, response);
	}

}
