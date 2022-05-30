package customer.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import customer.connection.ConnectionFactory;
import customer.model.Customer;
import  customer.model.Error;
import customer.util.constants;
import javassist.bytecode.stackmap.TypeData.ClassName;


public class CustomerService {
	Connection conn;
	Error error;

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}

	private static Logger logger = Logger.getLogger(ClassName.class.getName());
	
	

		
	private void recoverConnection() {
		int retry = 3;
		try {
			while (conn == null && retry != 0) {
				retry--;
				conn = ConnectionFactory.getConnection();
				if (conn != null)
					break;
				else if (retry == 0 && conn == null) {
					throw new Exception(
							"Could not get connection to the Database, Retries left: "
									+ retry);
				}
			}
		} catch (Exception e) {
			logger.log(
					Level.SEVERE,
					"CustomerDetails: Cannot get connection from datasource java:comp/env/jdbc/CustomerDB.",
					e);
			error = new Error();
			error.setCode(504);
			List<String> messages = new ArrayList<String>();
			messages.add("Cannot get connection from datasource java:comp/env/jdbc/CustomerDB");
			error.setMessages(messages);
			throw new WebApplicationException(Response
					.status(Status.GATEWAY_TIMEOUT).entity(error).build());
		}
	}

	public Customer getCustomerDetails(Long customerUniqueId, Connection conn) throws WebApplicationException, SQLException {
		PreparedStatement w1000Stm = null;

		ResultSet w1000rs = null;

		if (conn == null) {
			recoverConnection();
		}
		
			
			
List<Customer > customers=new ArrayList<Customer>();
		Customer customer=new Customer();
		String getW1000Query=constants.GET_CUSTOMER_DETAILS;
		try {
			w1000Stm = conn.prepareStatement(getW1000Query);
			w1000Stm.setLong(1, customerUniqueId);
			w1000rs = w1000Stm.executeQuery();
			
			if (w1000rs != null && w1000rs.next()) {
				customer.setCustomer_unique_number(w1000rs.getString("customer_unique_number"));
				customer.setLibrary(w1000rs.getString("LIBRARY"));
				customer.setCustomerId(w1000rs.getString("CUSTOMER_ID"));
				customer.setCompany(w1000rs.getString("COMPANY"));
				customer.setCustomerNumber(w1000rs.getString("CUSTOMER_NUMBER"));
				customer.setSystem_id(w1000rs.getString("SYSTEM_ID"));
				customer.setBill_to_unique_number(w1000rs.getString("bill_to_unique_number"));
				customer.setBill_to_company(w1000rs.getString("bill_to_company"));
				customer.setBill_to_customer_number(w1000rs.getString("bill_to_customer_number"));
				customer.setDate_start(w1000rs.getString("date_start"));
				customers.add(customer);
			}else {
				error = new Error();
				error.setCode(404);
				List<String> messages = new ArrayList<String>();
				messages.add("CustomerDetails :Requested Customers  was not found.");
				error.setMessages(messages);
				logger.log(Level.WARNING,
						"CustomerDetails :Requested Customers  was not found.");
				if (w1000rs != null)
					w1000rs.close();
				if (w1000Stm != null)
					w1000Stm.close();
				if (conn != null)
					conn.close();
				throw new WebApplicationException(Response.status(404).entity(error).build());
			}

		} catch (SQLException e) {
			logger.log(Level.SEVERE, "CustomerDetails: " + getW1000Query + " failed to execute.", e);
			error = new Error();
			error.setCode(500);
			List<String> messages = new ArrayList<String>();
			messages.add(e.getMessage());
			error.setMessages(messages);
			if (w1000rs != null)
				w1000rs.close();
			if (w1000Stm != null)
				w1000Stm.close();
			if (conn != null)
				conn.close();
			throw new WebApplicationException(Response.status(Status.INTERNAL_SERVER_ERROR).entity(error).build());
		} finally {
			try {
				if (w1000rs != null)
					w1000rs.close();
				if (w1000Stm != null)
					w1000Stm.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return customer;
	}
}
