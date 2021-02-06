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
 * Servlet implementation class UpdateCustomerServlet
 */
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCustomerServlet() {
    }

	/**
	 * This method called by Admin_Customer.jsp by Update button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer.setCustomer_ID(customerID);
		customer.setCustomer_Name(request.getParameter("customerName"));
		customer.setPassword(request.getParameter("password"));
		customer.setMobile_Number(request.getParameter("mobile"));
		customer.setNic_Number(request.getParameter("nic"));
		
		iCustomer.updateCustomer(customerID, customer);
		
		request.setAttribute("customer", customer);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListCustomers.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by EditCustomer.jsp by Update button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		response.setContentType("text/html");
		
		String customerID = request.getParameter("customerID");
		
		ICustomer iCustomer = new CustomerIMPL();
		Customer customer = new Customer();
		
		customer.setCustomer_ID(customerID);
		customer.setCustomer_Name(request.getParameter("customerName"));
		customer.setPassword(request.getParameter("password"));
		customer.setMobile_Number(request.getParameter("mobile"));
		customer.setNic_Number(request.getParameter("nic"));
		
		iCustomer.updateCustomer(customerID, customer);
		
		request.setAttribute("customer", customer);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Profile.jsp");
		dispatcher.forward(request, response);
	}

}
