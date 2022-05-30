package customer.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Customer {
	private String Library;
	private Long CustUnique;
	private String CustomerId;
	private String CustomerNumber;
	private String Company;
	private String customer_unique_number;
	private String  system_id;
	private String bill_to_unique_number;
	private String bill_to_company;
	private String bill_to_customer_number;
	private String date_start; 
	
	public String getBill_to_customer_number() {
		return bill_to_customer_number;
	}

	public void setBill_to_customer_number(String bill_to_customer_number) {
		this.bill_to_customer_number = bill_to_customer_number;
	}

	public String getBill_to_unique_number() {
		return bill_to_unique_number;
	}

	public void setBill_to_unique_number(String string) {
		this.bill_to_unique_number = string;
	}

	
	
	public String getDate_start() {
		return date_start;
	}

	public void setDate_start(String string) {
		this.date_start = string;
	}

	
	public String getBill_to_company() {
		return bill_to_company;
	}

	public void setBill_to_company(String bill_to_company) {
		this.bill_to_company = bill_to_company;
	}

	

	
	
	
	
	public String getCustomer_unique_number() {
		return customer_unique_number;
	}

	public void setCustomer_unique_number(String string) {
		this.customer_unique_number = string;
	}

	public String getSystem_id() {
		return system_id;
	}

	public void setSystem_id(String system_id) {
		this.system_id = system_id;
	}

	public String getCustomerNumber() {
		return CustomerNumber;
	}

	public void setCustomerNumber(String customerNumber) {
		CustomerNumber = customerNumber;
	}

	public String getCompany() {
		return Company;
	}

	public void setCompany(String company) {
		Company = company;
	}

	
	public String getCustomerId() {
		return CustomerId;
	}

	public void setCustomerId(String customerId) {
		CustomerId = customerId;
	}

	public Long getCustUnique() {
		return CustUnique;
	}

	public void setCustUnique(Long custUnique) {
		CustUnique = custUnique;
	}

	public String getLibrary() {
		return Library;
	}

	public void setLibrary(String library) {
		Library = library;
	}

	@Override
	public String toString() {
		return "Customer [Library=" + Library + ", CustUnique=" + CustUnique + ", CustomerId=" + CustomerId
				+ ", CustomerNumber=" + CustomerNumber + ", Company=" + Company + ",system_id="+system_id+",bill_to_unique_number="+bill_to_unique_number+
				",bill_to_company="+bill_to_company+",bill_to_customer_number="+bill_to_customer_number+",date_start="+date_start+"]";
	}

	
	
}
