package oop.servlet.account;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Account;
import oop.services.AccountIMPL;
import oop.services.IAccount;

/**
 * Servlet implementation class AddAccountServlet
 */

public class AddAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * This method called by AddAccount.jsp Add Account button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
			
		response.setContentType("text/html");
		
		Account account = new Account();
		
		String balance = request.getParameter("accountBalance");
		String customerID = request.getParameter("customerID");
		String accType = request.getParameter("accountType");
		
		if(balance.isEmpty() || customerID.isEmpty() || accType.isEmpty()){
			
			String error = "AccountBalance, CustomerID and AccountType cannot be empty";
			
			request.setAttribute("error", error);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddAccount.jsp");
			dispatcher.forward(request, response);
		} else{
			//set parameters to the object attributes
			account.setAccount_Type(request.getParameter("accountType"));
			account.setAccount_Balance(request.getParameter("accountBalance"));
			account.setCreate_Date(request.getParameter("createDate"));
			account.setCustomerID(request.getParameter("customerID"));
			
			IAccount iaccount = new AccountIMPL();
			
			iaccount.addAccount(account);
			
			request.setAttribute("account", account);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListAccounts.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
