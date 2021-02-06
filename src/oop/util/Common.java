package oop.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Common {

	public static final Logger LOG = Logger.getLogger(Common.class.getName());
	
	public static final Properties PROPERTIES = new Properties();
	
	static{
		
		try {
			
			/*Read the property*/ 
			PROPERTIES.load(Constants.class.getResourceAsStream(Constants.CONNECTION_PROPERTIES));
			
		} catch (IOException e) {
			
			LOG.log(Level.SEVERE, e.getMessage());
		}
	}
	
	public static String getNextAID(ArrayList<String> arraylist){
		String ID;
		
		int count = arraylist.size();
		
		count++;
		ID = Constants.ACCOUNT_PREFIX + count;
		
		if(arraylist.contains(ID)){
			count++;
			ID = Constants.ACCOUNT_PREFIX + count;
		}
		return ID;
		
	}
	
	public static String getNextCID(ArrayList<String> arraylist){
		String ID;
		
		int count = arraylist.size();
		
		count++;
		ID = Constants.CUSTOMER_PREFIX + count;
		
		if(arraylist.contains(ID)){
			count++;
			ID = Constants.CUSTOMER_PREFIX + count;
		}
		return ID;
		
	}

	public static String getNextTID(ArrayList<String> arraylist) {
		String ID;
		
		int count = arraylist.size();
		
		count++;
		ID = Constants.TRANSACTION_PREFIX + count;
		
		if(arraylist.contains(ID)){
			count++;
			ID = Constants.TRANSACTION_PREFIX + count;
		}
		return ID;
	}
	
	public static String getNextFID(ArrayList<String> arraylist) {
		String ID;
		
		int count = arraylist.size();
		
		count++;
		ID = Constants.FEEDBACK_PREFIX + count;
		
		if(arraylist.contains(ID)){
			count++;
			ID = Constants.FEEDBACK_PREFIX + count;
		}
		return ID;
	}
	
}
