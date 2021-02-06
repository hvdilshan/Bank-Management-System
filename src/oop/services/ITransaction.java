package oop.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import oop.classes.Transaction;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 *
 */
public interface ITransaction {
	
	public static final Logger LOG = Logger.getLogger(ICustomer.class.getName());
	
	/**
	 * Method to Add new transaction
	 * @param transaction
	 */
	public void addTransaction(Transaction transaction);
	
	/**
	 * Method to Get all transactions in the table
	 * @return ArrayList
	 */
	public ArrayList<Transaction> getAllTransactions();

	/**
	 * Method to Get transaction details by ID
	 * @param transactionID
	 * @return Transaction Object
	 */
	public Transaction getTransactionById(String transactionID);
	
	/**
	 * Method to Remove transaction by ID
	 * @param transactionID
	 */
	public void removeTransaction(String transactionID);
	
	/**
	 * Method to Update transaction Details by ID
	 * @param transactionID
	 * @param transaction
	 * @return 
	 */
	public Transaction updateTransaction(String transactionID, Transaction  transaction);
	
}
