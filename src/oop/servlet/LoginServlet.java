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
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
	}

	/**
	 * This method called by home.jsp login button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by Login.jsp login button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {

		//get user ID an password as parameter
		String customerID = request.getParameter("customerID");
		String password = request.getParameter("password");

		String error = null;//create string to get error message

		/*
		 * if values are null or empty load again Login.jsp and show error message
		 */
		if(customerID == null || password == null || customerID.length() == 0 || password.length() == 0 ){

			error = "User ID and Password cannot be Empty !";

			request.setAttribute("error", error);

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
			dispatcher.forward(request, response);
			
		} else{
			//if user ID and password equals to Admin data loads AdminPanel.jsp
			if(password.matches("Admin123") && customerID.matches("A1111")){

				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AdminPanel.jsp");
				dispatcher.forward(request, response);

			} else{
				ICustomer iCustomer = new CustomerIMPL();
				Customer customer = new Customer();

				//check is the customer has a account or not
				customer = iCustomer.checkCustomer(customerID, password);

				if(customer == null){
					error = "Invalid user ID or Password ! ";

					request.setAttribute("error", error);

					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/Login.jsp");
					dispatcher.forward(request, response);
					
				} else {
					//if no errors loads MainPage.jsp
					
					request.setAttribute("customer", customer);
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/MainPage.jsp");
					dispatcher.forward(request, response);
				}

			}
		}
	}


}
