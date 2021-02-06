package oop.servlet.transaction;

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
 * Servlet implementation class GetAccountServlet
 */
public class GetAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
	}

	/**
	 * This method called by SelectAccount.jsp by Submit button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String accountID = request.getParameter("accountID");
		
		IAccount iAccount = new AccountIMPL();
		Account account = new Account();
		
		account = iAccount.getAccountByID(accountID);//get the account by ID
		
		if(account != null){
			
			request.setAttribute("account", account);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddTransaction.jsp");
			dispatcher.forward(request, response);
			
		} else{
			//if account not exist show error message
			
			String error = "Invalid account number";
			
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/SelectAccount.jsp");
			dispatcher.forward(request, response);
		}
	}

}
