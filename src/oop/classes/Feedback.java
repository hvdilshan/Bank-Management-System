package oop.classes;

public class Feedback {

	private String feedbackID;
	private String date;
	private String message;
	private String customerID;
	
	//Getters for the parameters
	
	/**
	 * @return the feedbackID
	 */
	public String getFeedbackID() {
		return feedbackID;
	}
	
	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}
	
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	
	/**
	 * @return the customerID
	 */
	public String getCustomerID() {
		return customerID;
	}
	
	//Setters for the parameters
	
	/**
	 * @param feedbackID the feedbackID to set
	 */
	public void setFeedbackID(String feedbackID) {
		this.feedbackID = feedbackID;
	}
	
	/**
	 * @param date the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	
	/**
	 * @param customerID the customerID to set
	 */
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	
}
