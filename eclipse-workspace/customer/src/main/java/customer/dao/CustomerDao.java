package customer.dao;

import static customer.util.constants.DATA_BASE_CONNECTION_ERROR;

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
import customer.model.Error;
import customer.util.BuildError;
import customer.util.constants;
import javassist.bytecode.stackmap.TypeData.ClassName;

public class CustomerDao {
	private static Logger LOGGER = Logger.getLogger(ClassName.class.getName());

	public List<Long> getCustomerDetails(Long customerUniqueId) {
		List<Long> customer = new ArrayList<Long>();
		try (Connection conn = recoverConnection();
				PreparedStatement ps = createPreparedStatement(conn, customerUniqueId);
				ResultSet resultSet = ps.executeQuery();) {
			while (resultSet.next()) {
				customer.add(resultSet.getLong("SVCACCTID"));
			}
		} catch (SQLException s) {
			s.printStackTrace();
		}
		return customer;
	}
	private Connection recoverConnection() {
		Connection conn = null;
		int noOfAttemts = 3;
		try {
			while (conn == null && noOfAttemts != 0) {
				noOfAttemts--;
				conn = ConnectionFactory.getConnection();
				if (conn != null)
					break;
				else if (noOfAttemts == 0 && conn == null) {
					throw new Exception(DATA_BASE_CONNECTION_ERROR + noOfAttemts);
				}
			}
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE,
					"CustomerDetails: Cannot get connection from datasource java:comp/env/jdbc/CustomerDB", e);
			Error error = BuildError.buildDatabaseError();
			throw new WebApplicationException(Response.status(Status.GATEWAY_TIMEOUT).entity(error).build());
		}
		return conn;
	}
	
	private PreparedStatement createPreparedStatement(Connection conn, Long customerUniqueId) throws SQLException {
		PreparedStatement ps = conn.prepareStatement(constants.CUSTOMER_ID_NOT_FOUND);
		ps.setLong(1, customerUniqueId);
		return ps;
	}

	
}
