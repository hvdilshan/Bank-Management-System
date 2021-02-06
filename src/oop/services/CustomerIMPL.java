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

import oop.classes.Customer;
import oop.util.Common;
import oop.util.Constants;
import oop.util.DBconection;
import oop.util.RunQuery;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 */
public class CustomerIMPL implements ICustomer {

	public static final Logger LOG = Logger.getLogger(CustomerIMPL.class.getName());

	public static Connection connection;//get the database connection
	public static Statement statement;//use to send SQL statement in one time
	public static PreparedStatement preparedStatement;//use to send SQL statements in many times

	static{
		/*
		 * create table or drop if exist
		 * This method calls when create object from this class 
		 * */
		createCustomerTable();	
	}

	/**
	 * This method calls when create object from this class 
	 */
	public static void createCustomerTable() {

		try {
			//create the connection between server and the database
			connection = DBconection.getConnection();

			//create statement object for send SQL commands to the database
			statement = connection.createStatement();

			/*
			 * Drop Customer table
			 * At the beginning of the web start delete the created table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.DROP_CUSTOMER));

			/*
			 * Create Customer table
			 * At the beginning of the web start create the table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.CREATE_CUSTOMER_TABLE));

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException  e) {

			LOG.log(Level.SEVERE,e.getMessage());
		}

	}

	/**
	 * Method to add customers to Customer table
	 * @param customer
	 */
	@Override
	public void addCustomers(Customer customer) {

		//generate Customer ID start form C00000
		String customerID = Common.getNextCID(getCustomerID());

		try {
			//make a connection
			connection = DBconection.getConnection();
			//get insert query
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.INSERT_CUSTOMER));
			connection.setAutoCommit(false);

			customer.setCustomer_ID(customerID);
			//set values to the parameters
			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customer.getCustomer_ID());
			preparedStatement.setString(Constants.COLUMN_INDEX_TWO, customer.getCustomer_Name());
			preparedStatement.setString(Constants.COLUMN_INDEX_THREE, customer.getNic_Number());
			preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, customer.getMobile_Number());
			preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, customer.getPassword());

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
	 * Method to get all customers ID to generate next customer ID
	 * 
	 * @return ArrayList
	 */
	private ArrayList<String> getCustomerID() {

		ArrayList<String> arrayList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_CUSTOMERS_IDS));

			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				arrayList.add(resultSet.getString(Constants.COLUMN_INDEX_ONE));
			}

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

		return arrayList;
	}

	/**
	 * Method to get Customer details by ID
	 * @param customerID
	 * @return Customer
	 */
	@Override
	public Customer getCustomerByID(String customerID) {
		
		return getCustomerDetails(customerID).get(0);
	}

	/**
	 * Method to get all Customers details
	 * 
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Customer> getAllCustomers() {
		
		return getCustomerDetails(null);
	}


	/**
	 *  Method to get all Customers details
	 * @param customerID
	 * @return
	 */
	private ArrayList<Customer> getCustomerDetails(String customerID) {

		ArrayList <Customer> customerList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			//get result according to the ID
			if(customerID != null && !customerID.isEmpty())
			{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_CUSTOMER_BY_ID));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customerID);
			}

			//get all account details
			else{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_CUSTOMERS));
			}
			
			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				Customer customer = new Customer();

				customer.setCustomer_ID(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				customer.setCustomer_Name(resultSet.getString(Constants.COLUMN_INDEX_TWO));
				customer.setNic_Number(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				customer.setMobile_Number(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				customer.setPassword(resultSet.getString(Constants.COLUMN_INDEX_FIVE));
				customerList.add(customer);
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


		return customerList;
	}

	/**
	 * Method to Remove an customer by ID
	 * @param customerID
	 */
	@Override
	public void removeCustomer(String customerID) {

		if(customerID != null && !customerID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.REMOVE_CUSTOMER));
				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customerID);

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
	 * Method to Update an Customer
	 * @param customerID
	 * @param Customer
	 * @return Customer
	 */
	@Override
	public Customer updateCustomer(String customerID, Customer customer) {

		if(customerID != null && !customerID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.UPDATE_CUSTOMER));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customer.getCustomer_Name());
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, customer.getNic_Number());
				preparedStatement.setString(Constants.COLUMN_INDEX_THREE, customer.getMobile_Number());
				preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, customer.getPassword());
				preparedStatement.setString(Constants.COLUMN_INDEX_FIVE, customer.getCustomer_ID());



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

		return getCustomerByID(customerID);
	}

	/**
	 * Method to check customer, if exist return customer, 
	 * else customer does not exist or invalid user ID or Password return null value
	 * 
	 * @param customerID
	 * @param password
	 * 
	 * @return Customer
	 */
	@Override
	public Customer checkCustomer(String customerID, String password) {
		Customer customer = new Customer();

		try {
			connection = DBconection.getConnection();
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.CHECK_CUSTOMER));

			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customerID );
			preparedStatement.setString(Constants.COLUMN_INDEX_TWO, password );

			ResultSet resultSet = preparedStatement.executeQuery();
			
			//if resultSet have value, it assign to the object
			if(resultSet.next()){
				customer.setCustomer_ID(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				customer.setCustomer_Name(resultSet.getString(Constants.COLUMN_INDEX_TWO));
				customer.setNic_Number(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				customer.setMobile_Number(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				customer.setPassword(resultSet.getString(Constants.COLUMN_INDEX_FIVE));

				return customer;
			} else{
				//if customer does not exist or invalid user ID or Password
				return null;
			}

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException e) {
			e.printStackTrace();

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
		return customer;
	}


}
