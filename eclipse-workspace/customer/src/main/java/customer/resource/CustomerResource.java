package customer.resource;
import java.sql.Connection;
import java.sql.SQLException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import customer.connection.ConnectionFactory;
import customer.model.Customer;
import customer.service.CustomerService;



@Path("/")

public class CustomerResource {
	Connection conn;

	@Path("{customerUniqueId}")
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCustomerDetails(
			@PathParam("customerUniqueId") Long customerUniqueId)
			throws SQLException, WebApplicationException {

		

		
		Customer customer=new Customer();

		CustomerService service = new CustomerService();
		if (conn == null) {
			try {
				conn = ConnectionFactory.getConnection();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		service.setConn(conn);
		// get library list ordered by name
		customer = service.getCustomerDetails(customerUniqueId,conn);

	

		try {
			conn = service.getConn();
			if (conn != null)
				conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return Response.status(Status.OK).entity(customer).build();
	}
}
