package oop.classes;

/**
 * Account Details class
 * @author #delta4 Team
 *
 */
public class Account {

	private String account_NO; 
	private String account_Type; 
	private String account_Balance; 
	private String create_Date;
	private String customerID;

	//Getters for the parameters

	/**
	 * @return the account_NO
	 */
	public String getAccount_NO() {
		return account_NO;
	}

	/**
	 * @return the account_Type
	 */
	public String getAccount_Type() {
		return account_Type;
	}

	/**
	 * @return the account_Balance
	 */
	public String getAccount_Balance() {
		return account_Balance;
	}

	/**
	 * @return the create_Date
	 */
	public String getCreate_Date() {
		return create_Date;
	}

	/**
	 * 
	 * @return the Customer ID
	 */
	public String getCustomerID() {
		return customerID;
	}

	//Setters for the parameters

	/**
	 * @param account_NO the account_NO to set
	 */
	public void setAccount_NO(String account_NO) {
		this.account_NO = account_NO;
	}

	/**
	 * @param account_Type the account_Type to set
	 */
	public void setAccount_Type(String account_Type) {
		this.account_Type = account_Type;
	}

	/**
	 * @param account.account_Balance the account_Balance to set
	 */
	public void setAccount_Balance(String balance) {
		this.account_Balance = balance;
	}

	/**
	 * @param string the create_Date to set
	 */
	public void setCreate_Date(String string) {
		this.create_Date = string;
	}

	/**
	 * @param string the create_Date to set
	 */
	public void setCustomerID(String cID) {
		this.customerID = cID;
	}

}
