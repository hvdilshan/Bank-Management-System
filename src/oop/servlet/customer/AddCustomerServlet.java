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
 * Servlet implementation class AddCustomer
 */

public class AddCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddCustomerServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * This method called by AddCustomer.jsp by Add Customer button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		Customer customer = new Customer();
		
		//set parameters to the object parameters
		customer.setCustomer_Name(request.getParameter("customerName"));
		customer.setNic_Number(request.getParameter("nic"));
		customer.setMobile_Number(request.getParameter("mobile"));
		customer.setPassword(request.getParameter("password"));
		
		String password = request.getParameter("password");
		String rePassword = request.getParameter("rePassword");
		
		ICustomer icustomer = new CustomerIMPL();
		
		//check the password and re entered password is same
		if(password.equals(rePassword)){
			icustomer.addCustomers(customer);
			
			request.setAttribute("customer", customer);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			dispatcher.forward(request, response);
		} else{
			
			String error = "Password does not match !";
			
			request.setAttribute("customer", customer);
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddCustomer.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
