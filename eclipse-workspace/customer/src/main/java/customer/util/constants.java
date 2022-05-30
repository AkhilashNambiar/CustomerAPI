package customer.util;

public class constants {
	public final static String GET_CUSTOMER_DETAILS = "SELECT  customer_unique_number,library,system_id,customer_id,company,customer_number,bill_to_unique_number,bill_to_company,bill_to_customer_number,date_start FROM MASLIBRS.W1000 WHERE CUSTOMER_UNIQUE_NUMBER =?";

	
	public final static String CUSTOMER_ID_NOT_FOUND = " Requested Customer Unique Id was not found.";

	public final static String SQL_EXCEPTION = "SQL Exception was encountered while fetching GeoCoordinates.";

	

	
	public final static String DATA_BASE_CONNECTION_ERROR = "Could not get connection to the Database, Retries left: ";

	

	public final static String EMTY_SPACE = " ";
}
