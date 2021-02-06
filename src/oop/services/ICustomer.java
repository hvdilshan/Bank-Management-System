package oop.services;

import java.util.ArrayList;
import java.util.logging.Logger;

import oop.classes.Customer;

/**
 * 
 * @author #delta4 Team
 * @contact delta4team@gmail.com
 * 
 */
public interface ICustomer {
	
	public static final Logger LOG = Logger.getLogger(ICustomer.class.getName());
	
	/**
	 * Method to add customers to Customer table
	 * @param customer
	 */
	public void addCustomers(Customer customer);
	
	/**
	 * Method to get customer details by ID
	 * @param customerID
	 * @return Customer
	 */
	public Customer getCustomerByID(String customerID);
	
	/**
	 * Method to get all customer details
	 * @return
	 */
	public ArrayList<Customer> getAllCustomers();
	
	/**
	 * Method to remove customer
	 * @param customerID
	 */
	public void removeCustomer(String customerID);

	/**
	 * Method to update customer details
	 * @param customerID
	 * @return
	 */
	public Customer updateCustomer(String customerID, Customer customer);
	
	/**
	 * Method to check a customer using ID and password
	 * @param customerID
	 * @param password
	 * @return customer
	 */
	public Customer checkCustomer(String customerID, String password);
}
