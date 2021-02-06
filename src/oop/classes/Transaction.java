package oop.classes;

public class Transaction {

	private String transactionID;
	private String payFrom;
	private String payTo;
	private String amount;
	
	//Getters for the parameters

	/**
	 * @return the transactionID
	 */
	public String getTransactionID() {
		return transactionID;
	}
	
	/**
	 * @return the payFrom
	 */
	public String getPayFrom() {
		return payFrom;
	}
	
	/**
	 * @return the payTo
	 */
	public String getPayTo() {
		return payTo;
	}
	
	/**
	 * @return the amount
	 */
	public String getAmount() {
		return amount;
	}
	
	//Setters for the parameters
	
	/**
	 * @param transactionID the transactionID to set
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	/**
	 * @param payFrom the payFrom to set
	 */
	public void setPayFrom(String payFrom) {
		this.payFrom = payFrom;
	}

	/**
	 * @param payTo the payTo to set
	 */
	public void setPayTo(String payTo) {
		this.payTo = payTo;
	}

	/**
	 * @param amount the amount to set
	 */
	public void setAmount(String amount) {
		this.amount = amount;
	}
	
}
