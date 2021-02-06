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

import oop.classes.Transaction;
import oop.util.Common;
import oop.util.Constants;
import oop.util.DBconection;
import oop.util.RunQuery;

/*
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 */
public class TransactionIMPL implements ITransaction {

	public static final Logger LOG = Logger.getLogger(TransactionIMPL.class.getName());

	public static Connection connection;//get the database connection
	public static Statement statement;//use to send SQL statement in one time
	public static PreparedStatement preparedStatement;//use to send SQL statements in many times
	
	static{
		/*
		 * create table or drop if exist
		 * This method calls when create object from this class 
		 * */
		createTransactionTable();
	}

	/**
	 * This method calls when create object from this class 
	 */
	private static void createTransactionTable() {
		try {
			//create the connection between server and the database
			connection = DBconection.getConnection();

			//create statement object for send SQL commands to the database
			statement = connection.createStatement();

			/*
			 * Drop Transaction table
			 * At the beginning of the web start delete the created table
			 **/
			statement.executeUpdate(RunQuery.queryByID(Constants.DROP_TRANSACTION));

			/*
			 * Create Transaction table
			 * At the beginning of the web start create the table
			 **/
			statement.executeUpdate(RunQuery.queryByID(Constants.CREATE_TRANSACTION_TABLE));

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {
			LOG.log(Level.SEVERE,e.getMessage());
		}
	}

	/**
	 * Method to Add new transaction
	 * @param transaction
	 */
	@Override
	public void addTransaction(Transaction transaction) {
		//generate Transaction ID start form T00000
		String transactionID = Common.getNextTID(getTransactionID());


		try {
			//make a connection
			connection = DBconection.getConnection();
			//get insert query
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.INSERT_TRANSACTION));
			connection.setAutoCommit(false);

			//set values to columns
			transaction.setTransactionID(transactionID);
			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, transaction.getTransactionID());
			preparedStatement.setString(Constants.COLUMN_INDEX_TWO, "C0001" );
			preparedStatement.setString(Constants.COLUMN_INDEX_THREE, transaction.getPayFrom());
			preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, transaction.getPayTo());
			preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, transaction.getAmount());

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
	 * Method to get all transactions ID to generate next transactions ID
	 * 
	 * @return ArrayList
	 */
	private ArrayList<String> getTransactionID() {

		ArrayList<String> transactionsID = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_TRANSACTIONS_IDs));

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while (resultSet.next()) {

				transactionsID.add(resultSet.getString(Constants.COLUMN_INDEX_ONE));
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

		return transactionsID;
	}

	/**
	 * Method to Get all transactions in the table
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Transaction> getAllTransactions() {

		return getTransactionDetails(null);
	}

	/**
	 * Method to Get transaction details by ID
	 * @param transactionID
	 * @return Transaction Object
	 */
	@Override
	public Transaction getTransactionById(String transactionID) {

		return getTransactionDetails(transactionID).get(0);
	}

	/**
	 * Method to get all Transaction details
	 * @param transactionID
	 * @return All Transactions
	 */
	private ArrayList<Transaction> getTransactionDetails(String transactionID) {

		ArrayList <Transaction> transactionList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			//get result according to the ID
			if(transactionID != null && !transactionID.isEmpty())
			{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_TRANSACTION_BY_ID));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, transactionID);
			}

			//get all account details
			else{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_TRANSACTIONS));
			}

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				Transaction transaction = new Transaction();

				transaction.setTransactionID(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				transaction.setPayFrom(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				transaction.setPayTo(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				transaction.setAmount(resultSet.getString(Constants.COLUMN_INDEX_FIVE));
				transactionList.add(transaction);
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


		return transactionList;

	}

	/**
	 * Method to Remove transaction by ID
	 * @param transactionID
	 */
	@Override
	public void removeTransaction(String transactionID) {

		if(transactionID != null && !transactionID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.DROP_TRANSACTION_BY_ID));
				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, transactionID);

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
	 * Method to Update transaction Details by ID
	 * @param transactionID
	 * @param transaction
	 * @return 
	 */
	@Override
	public Transaction updateTransaction(String transactionID, Transaction transaction) {

		if(transactionID != null && !transactionID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.UPDATE_TRANSACTION));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, transaction.getPayFrom());
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, transaction.getPayTo());
				preparedStatement.setString(Constants.COLUMN_INDEX_THREE, transaction.getAmount());
				preparedStatement.setString(Constants.COLUMN_INDEX_FOUR,transaction.getTransactionID() );

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

		return getTransactionById(transactionID);
	}

}
