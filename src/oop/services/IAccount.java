package oop.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import oop.classes.Account;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 *
 */
public interface IAccount {

	public static final Logger LOG = Logger.getLogger(IAccount.class.getName());

	/**
	 * Method to add Account to Account table
	 * @param account
	 */
	public void addAccount(Account account);

	/**
	 * Method to get account by ID
	 * @param ID
	 * @return Account
	 */
	public Account getAccountByID(String accountID);

	/**
	 * 
	 * Method to get all Accounts details
	 * @return ArrayList
	 */
	public ArrayList<Account> getAllAccounts();

	/**
	 * Method to delete an account
	 * @param accountID
	 */
	public void removeAccount(String accountID);

	/**
	 * Method to update an account
	 * @param accountID
	 */
	public Account updateAccount(String accountID ,Account account);
	
	/**
	 * Method to update account balance
	 * @param accountID
	 * @param balance
	 */
	public void updateAccountBalance(String accountID, String balance);
}
