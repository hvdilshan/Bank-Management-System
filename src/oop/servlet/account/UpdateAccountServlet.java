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
 * Servlet implementation class EditAccountrServlet
 */
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateAccountServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	}

	/**
	 * This method called by EditAccount.jsp by Update button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		response.setContentType("text/html");
		
		IAccount iaccount = new AccountIMPL();
		
		Account account= new Account();
		
		String accountID = request.getParameter("accountID");
		
		account.setAccount_NO(accountID);
		account.setCustomerID(request.getParameter("customerID"));
		account.setAccount_Type(request.getParameter("accountType"));
		account.setAccount_Balance(request.getParameter("accountBalance"));
		account.setCreate_Date(request.getParameter("createDate"));
		
		iaccount.updateAccount(accountID, account);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListAccounts.jsp");
		dispatcher.forward(request, response);
	}

}
