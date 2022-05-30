package customer.util;

import static customer.util.constants.CUSTOMER_ID_NOT_FOUND;

import java.util.ArrayList;
import java.util.List;
import  customer.model.Error;


public class BuildError {

	public static Error buildError(Long customerUniqueId) {
		Error error = new Error();
		error.setCode(404);
		List<String> messages = new ArrayList<String>();
		messages.add(customerUniqueId + CUSTOMER_ID_NOT_FOUND);
		error.setMessages(messages);
		return error;
	}
	public static Error buildDatabaseError() {
		Error error = new Error();
		error.setCode(504);
		List<String> messages = new ArrayList<String>();
		messages.add("Cannot get connection from datasource java:comp/env/jdbc/CustomerDB");
		error.setMessages(messages);
		return error;
	}
}
