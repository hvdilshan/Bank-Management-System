package oop.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import oop.classes.Feedback;

/**
 * 
 * @author #delta4 Team
 *@contact delta4team@gmail.com
 * 
 */
public interface IFeedback {

	public static final Logger LOG = Logger.getLogger(ITransaction.class.getName());
	
	/**
	 * Method to add feedback
	 * @param feedback
	 */
	public void addFeedback(Feedback feedback);
	
	/**
	 * Method to update the feedback
	 * @param feedback
	 */
	public Feedback updateFeedback(String feedbackID,Feedback feedback);
	
	/**
	 * Method to remove a feedback
	 * @param feedbackID
	 */
	public void removeFeedback(String feedbackID);
	
	/**
	 * Method to get feedback by ID
	 * @param feedbackID
	 * @return feedback
	 */
	public Feedback getFeedbackByID(String feedbackID);
	
	/**
	 * Method to get all feedbacks
	 * @return ArrayList
	 */
	public ArrayList<Feedback> getAllFeedbacks();
	
	/**
	 * Method to get feedbacks by customer ID
	 * @return ArrayList
	 * @param customerID
	 * 
	 */
	public ArrayList<Feedback> getFeedbackByCustomer(String customerID);
}
