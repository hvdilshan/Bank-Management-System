package oop.services;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import oop.classes.Account;
import oop.util.Common;
import oop.util.Constants;
import oop.util.DBconection;
import oop.util.RunQuery;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 */
public class AccountIMPL implements IAccount {

	public static final Logger LOG = Logger.getLogger(AccountIMPL.class.getName());

	public static Connection connection;//get the database connection
	public static Statement statement;//use to send SQL statement in one time
	public static PreparedStatement preparedStatement;//use to send SQL statements in many times

	static{
		/*
		 * create table or drop if exist
		 * This method calls when create object from this class 
		 * */
		createAccountTable();
	}
	
	/**
	 * This method calls when create object from this class 
	 */
	public static void createAccountTable(){

		try {
			//create the connection between server and the database
			connection = DBconection.getConnection();
			
			//create statement object for send SQL commands to the database
			statement = connection.createStatement();

			/*
			 * Drop Account table
			 * At the beginning of the web start delete the created table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.DROP_ACCOUNT));

			/*
			 * Create Account table
			 * At the beginning of the web start create the table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.CREATE_ACCOUNT_TABLE));

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {
			LOG.log(Level.SEVERE,e.getMessage());
		}
	}
	
	/**
	 * Method to add new account to the database
	 * @param account
	 */
	@Override
	public void addAccount(Account account) {

		//generate Account ID start form A00000
		String accountID = Common.getNextAID(getAccountID());


		try {
			//make a connection
			connection = DBconection.getConnection();
			//get insert query
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.INSERT_ACCOUNT));
			connection.setAutoCommit(false);

			//set values to the parameters
			account.setAccount_NO(accountID);
			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, account.getAccount_NO());
			preparedStatement.setString(Constants.COLUMN_INDEX_TWO, account.getAccount_Type());
			preparedStatement.setString(Constants.COLUMN_INDEX_THREE, account.getAccount_Balance());
			preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, account.getCreate_Date());
			preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, account.getCustomerID());

			preparedStatement.execute();
			connection.commit();

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
			
			LOG.log(Level.SEVERE, e.getMessage());
			
		}finally {
			/*
			 * close database connection and prepared statement
			 * 
			 */
			try {
				if(preparedStatement != null){

					preparedStatement.close();
				}

				if(connection != null){

					connection.close();
				}

			} catch (SQLException e) {

				LOG.log(Level.SEVERE, e.getMessage());
			}
		}
	}

	/**
	 * Method to get all accounts ID to generate next account ID
	 * 
	 * @return ArrayList
	 */
	private ArrayList<String> getAccountID() {

		ArrayList<String> accountsID = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_ACCOUNTS_IDS));

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				accountsID.add(resultSet.getString(Constants.COLUMN_INDEX_ONE));
			}

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}
		finally {
			/*
			 * close database connection and prepared statement
			 * 
			 */
			try {
				if(preparedStatement != null){

					preparedStatement.close();
				}

				if(connection != null){

					connection.close();
				}

			} catch (SQLException e) {
				LOG.log(Level.SEVERE, e.getMessage());
			}
		}

		return accountsID;
	}

	/**
	 * Method to get account details by ID
	 * @param AccountID
	 * @return Account
	 */
	@Override
	public Account getAccountByID(String accountID) {

		ArrayList<Account> account = getAcountDetails(accountID);
		
		//if account is not available returns null value
		if(account.isEmpty()){

			return null;

		}else{

			return account.get(0);//0 th index of the array
		}

	}

	/**
	 * Method to get all account details
	 * 
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Account> getAllAccounts() {

		return getAcountDetails(null);
	}

	/**
	 * Method to get all accounts details
	 * @param accountID
	 * @return All accounts
	 */
	private ArrayList<Account> getAcountDetails(String accountID) {

		ArrayList <Account> accountList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			//get result according to the ID
			if(accountID != null && !accountID.isEmpty())
			{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_ACCOUNT_BY_ID));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, accountID);
			}

			//get all account details
			else{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_ACCOUNTS));
			}
			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				//create account objects until exit from the while loop
				Account account = new Account();

				account.setAccount_NO(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				account.setAccount_Type(resultSet.getString(Constants.COLUMN_INDEX_TWO));
				account.setAccount_Balance(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				account.setCreate_Date(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				account.setCustomerID(resultSet.getString(Constants.COLUMN_INDEX_FIVE));
				accountList.add(account);
			}



		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {
			LOG.log(Level.SEVERE, e.getMessage());
		}finally {
			/*
			 * close database connection and prepared statement
			 * 
			 */
			try {
				if(preparedStatement != null){
					preparedStatement.close();
				}
				if(connection != null){
					connection.close();
				} 
			}catch (SQLException e) {
				LOG.log(Level.SEVERE, e.getMessage());
			}
		}

		return accountList;
	}
	
	/**
	 * Method to Remove an account by ID
	 * @param accountID
	 */
	@Override
	public void removeAccount(String accountID) {

		//remove account according to the ID
		if(accountID != null && !accountID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.REMOVE_ACCOUNT));
				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, accountID);

				preparedStatement.executeUpdate();

			}catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {

				LOG.log(Level.SEVERE, e.getMessage());
			}finally{
				/*
				 * close database connection and prepared statement
				 * 
				 */
				try {
					if(preparedStatement != null){
						preparedStatement.close();
					}
					if(connection != null){
						connection.close();
					} 
				}catch (SQLException e) {
					LOG.log(Level.SEVERE, e.getMessage());
				}

			}
		}
	}

	/**
	 * Method to Update an account
	 * @param accountID
	 * @param account
	 * @return Account
	 */
	@Override
	public Account updateAccount(String accountID, Account account) {

		if(accountID != null && !accountID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.UPDATE_ACCOUNT));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, account.getAccount_Type());
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, account.getAccount_Balance());
				preparedStatement.setString(Constants.COLUMN_INDEX_THREE, account.getCreate_Date());
				preparedStatement.setString(Constants.COLUMN_INDEX_FOUR,accountID );

				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				LOG.log(Level.SEVERE, e.getMessage());
			}finally {
				/*
				 * close database connection and prepared statement
				 * 
				 */
				try {
					if(preparedStatement != null ){
						preparedStatement.close();
					}
					if(connection != null){
						connection.close();
					} 
				}catch (SQLException e) {
					LOG.log(Level.SEVERE, e.getMessage());
				}

			}
		}

		return getAccountByID(accountID);
	}
	
	/**
	 * Method to Update account balance when transaction occurs
	 * @param accountID
	 * @param balance
	 */
	@Override
	public void updateAccountBalance(String accountID, String balance) {

		if(accountID != null && !accountID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.UPDATE_ACCOUNT_BALANCE));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, balance);
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, accountID);

				preparedStatement.executeUpdate();

			} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {
				LOG.log(Level.SEVERE, e.getMessage());
			}finally {
				/*
				 * close database connection and prepared statement
				 * 
				 */
				try {
					if(preparedStatement != null ){
						preparedStatement.close();
					}
					if(connection != null){
						connection.close();
					} 
				}catch (SQLException e) {
					LOG.log(Level.SEVERE, e.getMessage());
				}

			}
		}
	}
}
