package customer.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Error {

	int code;
	List<String> messages;
	String fields;
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public List<String> getMessages() {
		return messages;
	}
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	public String getFields() {
		return fields;
	}
	public void setFields(String fields) {
		this.fields = fields;
	}
}
