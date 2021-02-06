package oop.classes;

/**
 * Customer Details class
 * @author #delta4 Team
 *
 */
public class Customer {

	private String customer_ID;
	private String customer_Name;
	private String nic_Number;
	private String mobile_Number; 
	private String password;

	//Getters for the parameters

	/**
	 * @return the customer_ID
	 */
	public String getCustomer_ID() {
		return customer_ID;
	}

	/**
	 * @return the customer_Name
	 */
	public String getCustomer_Name() {
		return customer_Name;
	}

	/**
	 * @return the nic_Number
	 */
	public String getNic_Number() {
		return nic_Number;
	}

	/**
	 * @return the mobile_Number
	 */
	public String getMobile_Number() {
		return mobile_Number;
	}

	/**
	 * 
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	//Setters for the parameters

	/**
	 * @param customer_ID the customer_ID to set
	 */
	public void setCustomer_ID(String customer_ID) {
		this.customer_ID = customer_ID;
	}

	/**
	 * @param customer_Name the customer_Name to set
	 */
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}

	/**
	 * @param nic_Number the nic_Number to set
	 */
	public void setNic_Number(String nic_Number) {
		this.nic_Number = nic_Number;
	}

	/**
	 * @param number the mobile_Number to set
	 */
	public void setMobile_Number(String number) {
		this.mobile_Number = number;
	}

	/**
	 * 
	 * @param password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
