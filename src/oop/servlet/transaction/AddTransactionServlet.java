package oop.servlet.transaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Account;
import oop.classes.Transaction;
import oop.services.AccountIMPL;
import oop.services.IAccount;
import oop.services.ITransaction;
import oop.services.TransactionIMPL;

/**
 * Servlet implementation class TransactionServlet
 */

public class AddTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTransactionServlet() {
        super();
    }

	/**
	 * This method called by MainPage.jsp by Transaction button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/SelectAccount.jsp");
		dispatcher.forward(request, response);
	}

	/**
	 * This method called by AddTransaction.jsp by Submit button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		response.setContentType("text/html");
		
		String accountID = request.getParameter("accountID");
		IAccount iAccount = new AccountIMPL();
		Account account = new Account();
		
		account = iAccount.getAccountByID(accountID);
		
		//get account current balance and transaction amount
		String balance = account.getAccount_Balance();
		String pay = request.getParameter("amount");
		
		//convert String values to double
		double bal = Double.valueOf(balance);
		double paying = Double.valueOf(pay);
		
		//if current balance less than paying amount show error message
		if(paying > bal){
			
			String error = "Unsufficient balance. your account balance is ";
			
			request.setAttribute("error", error);
			request.setAttribute("account", account);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/AddTransaction.jsp");
			dispatcher.forward(request, response);
			
		} else{
			
			bal = bal - paying;//get the remaining account balance after transaction
			
			balance = String.valueOf(bal);
			
			Transaction transaction = new Transaction();
			
			transaction.setPayFrom(request.getParameter("payFrom"));
			transaction.setPayTo(request.getParameter("payTo"));
			transaction.setAmount(request.getParameter("amount"));
			
			ITransaction iTransaction = new TransactionIMPL();
			
			iAccount.updateAccountBalance(accountID, balance);//update account balance
			iTransaction.addTransaction(transaction);
			
			request.setAttribute("transaction", transaction);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListTransactions.jsp");
			dispatcher.forward(request, response);
		}
		
	}

}
