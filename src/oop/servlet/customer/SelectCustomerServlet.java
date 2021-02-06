package oop.servlet.customer;

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
 * Servlet implementation class EditCustomerServlet
 */

public class SelectCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectCustomerServlet() {
        super();
    }

	/**
	 * This method called by MainPage.jsp by Show Profile button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	
		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer = iCustomer.getCustomerByID(customerID);
		
		request.setAttribute("customer", customer);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Profile.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by Profile.jsp by Edit button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer = iCustomer.getCustomerByID(customerID);
		
		request.setAttribute("customer", customer);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/EditCustomer.jsp");
		dispatcher.forward(request, response);
	}

}
