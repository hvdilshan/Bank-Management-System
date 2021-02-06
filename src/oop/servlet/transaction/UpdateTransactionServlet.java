package oop.servlet.transaction;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import oop.classes.Transaction;
import oop.services.ITransaction;
import oop.services.TransactionIMPL;

/**
 * Servlet implementation class EditTransactionServlet
 */

public class UpdateTransactionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateTransactionServlet() {
        super();
    }

	/**
	 * This method called by ListTransaction.jsp by EditTransaction button
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String transactionID = request.getParameter("transactionID");
		
		ITransaction iTransaction = new TransactionIMPL();
		Transaction transaction = iTransaction.getTransactionById(transactionID);
		
		request.setAttribute("transaction", transaction);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/EditTransaction.jsp");
		dispatcher.forward(request, response);
		
	}

	/**
	 * This method called by EditTransaction.jsp by Update button
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
	
		response.setContentType("text/html");
		
		String transactionID = request.getParameter("transactionID");
		
		Transaction transaction = new Transaction();
		
		//Set parameters to object parameters
		transaction.setTransactionID(transactionID);
		transaction.setPayFrom(request.getParameter("payFrom"));
		transaction.setPayTo(request.getParameter("payTo"));
		transaction.setAmount(request.getParameter("amount"));
		
		ITransaction iTransaction = new TransactionIMPL();
		
		iTransaction.updateTransaction(transactionID, transaction);
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/WEB-INF/views/ListTransactions.jsp");
		dispatcher.forward(request, response);
	}

}
