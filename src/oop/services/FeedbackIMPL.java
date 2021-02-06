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

import oop.classes.Feedback;
import oop.util.Common;
import oop.util.Constants;
import oop.util.DBconection;
import oop.util.RunQuery;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 */
public class FeedbackIMPL implements IFeedback {

	public static final Logger LOG = Logger.getLogger(Feedback.class.getName());

	public static Connection connection;//get the database connection
	public static Statement statement;//use to send SQL statement in one time
	public static PreparedStatement preparedStatement;//use to send SQL statements in many times

	static{
		/*
		 * create table or drop if exist
		 * This method calls when create object from this class 
		 * */
		createFeedbackTable();
	}

	/**
	 * This method calls when create object from this class 
	 */
	private static void createFeedbackTable() {

		try {
			//create the connection between server and the database
			connection = DBconection.getConnection();

			//create statement object for send SQL commands to the database
			statement = connection.createStatement();

			/*
			 * Drop feedback table
			 * At the beginning of the web start delete the created table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.DROP_FEEDBACK));

			/*
			 * Create feedback table
			 * At the beginning of the web start create the table
			 */
			statement.executeUpdate(RunQuery.queryByID(Constants.CREATE_FEEDBACK_TABLE));

		} catch (ClassNotFoundException | SQLException | SAXException | IOException | ParserConfigurationException  e) {

			LOG.log(Level.SEVERE,e.getMessage());
		}


	}

	/**
	 * Method to add new feedback to the database
	 * @param feedback
	 */
	@Override
	public void addFeedback(Feedback feedback) {

		//generate feedback ID start form F00000
		String feedbackID = Common.getNextFID(getFeedbackID());

		try {
			//make a connection
			connection = DBconection.getConnection();
			//get insert query
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.INSERT_FEEDBACK));
			connection.setAutoCommit(false);

			feedback.setFeedbackID(feedbackID);
			//set values to the parameters
			preparedStatement.setString(Constants.COLUMN_INDEX_ONE, feedback.getFeedbackID());
			preparedStatement.setString(Constants.COLUMN_INDEX_TWO, feedback.getCustomerID());
			preparedStatement.setString(Constants.COLUMN_INDEX_THREE, feedback.getDate());
			preparedStatement.setString(Constants.COLUMN_INDEX_FOUR, feedback.getMessage());

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
	 *  Method to get all feedbacks ID to generate next feedback ID
	 * @return ArrayList
	 */
	private ArrayList<String> getFeedbackID() {

		ArrayList<String> arrayList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();
			preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_FEEDBACKS_IDs));

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){

				arrayList.add(resultSet.getString(Constants.COLUMN_INDEX_ONE));
			}

		} catch (SQLException | SAXException | IOException | ParserConfigurationException | ClassNotFoundException e) {

			LOG.log(Level.SEVERE, e.getMessage());
		}finally {

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
	 * Method to Update Feedback
	 * @param feedbackID
	 * @param feedback
	 * @return Feedback
	 */
	@Override
	public Feedback updateFeedback(String feedbackID, Feedback feedback) {


		if(feedbackID != null && !feedbackID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.UPDATE_FEEDBACK));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, feedback.getMessage());
				preparedStatement.setString(Constants.COLUMN_INDEX_TWO, feedback.getDate());
				preparedStatement.setString(Constants.COLUMN_INDEX_THREE, feedback.getFeedbackID());

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

		return getFeedbackByID(feedbackID);
	}

	/**
	 * Method to Remove Feedback by ID
	 * @param feedbackID
	 */
	@Override
	public void removeFeedback(String feedbackID) {

		if(feedbackID != null && !feedbackID.isEmpty()){

			try {
				connection = DBconection.getConnection();

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.DROP_FEEDBACK_BY_ID));
				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, feedbackID);

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
	 * Method to get Feedback details by ID
	 * @param feedbackID
	 * @return Feedback
	 */
	@Override
	public Feedback getFeedbackByID(String feedbackID) {

		return getFeedbackDetails(feedbackID).get(0);
	}

	/**
	 * Method to get all Feedback details
	 * 
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Feedback> getAllFeedbacks() {
		return getFeedbackDetails(null);
	}

	
	private ArrayList<Feedback> getFeedbackDetails(String feedbackID) {

		ArrayList <Feedback> feedbackList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			//get result according to the ID
			if(feedbackID != null && !feedbackID.isEmpty())
			{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_FEEDBACK_BY_ID));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, feedbackID);
			}

			//get all feedback details
			else{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_FEEDBACKS));
			}

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				Feedback feedback = new Feedback();

				feedback.setFeedbackID(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				feedback.setCustomerID(resultSet.getString(Constants.COLUMN_INDEX_TWO));
				feedback.setDate(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				feedback.setMessage(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				feedbackList.add(feedback);
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


		return feedbackList;
	}
	
	/**
	 * Method to get all Feedback details by customer
	 * @param customerID
	 * @return ArrayList
	 */
	@Override
	public ArrayList<Feedback> getFeedbackByCustomer(String customerID) {
		ArrayList <Feedback> feedbackList = new ArrayList<>();

		try {
			connection = DBconection.getConnection();

			//get result according to the ID
			if(customerID != null && !customerID.isEmpty())
			{

				preparedStatement = connection.prepareStatement(RunQuery.queryByID(Constants.GET_FEEDBACKS_BY_CUSTOMER_ID));

				preparedStatement.setString(Constants.COLUMN_INDEX_ONE, customerID);
			}

			/*
			 *the ResultSet object takes executed result to it and stored as rows
			 *next() method use in while loop for get out the data
			 */
			ResultSet resultSet = preparedStatement.executeQuery();

			while(resultSet.next()){
				Feedback feedback = new Feedback();

				feedback.setFeedbackID(resultSet.getString(Constants.COLUMN_INDEX_ONE));
				feedback.setCustomerID(resultSet.getString(Constants.COLUMN_INDEX_TWO));
				feedback.setDate(resultSet.getString(Constants.COLUMN_INDEX_THREE));
				feedback.setMessage(resultSet.getString(Constants.COLUMN_INDEX_FOUR));
				feedbackList.add(feedback);
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


		return feedbackList;
	}

}
