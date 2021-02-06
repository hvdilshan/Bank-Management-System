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
 * Servlet implementation class SelectAccountServlet
 */

public class SelectAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SelectAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * This method called by ListAccounts.jsp by Edit Account button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		String accountID = request.getParameter("accountID");
		
		IAccount iAccount = new AccountIMPL();
		Account account = new Account();
		
		account = iAccount.getAccountByID(accountID);
		
		request.setAttribute("account", account);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/EditAccount.jsp");
		dispatcher.forward(request, response);
	}

}
