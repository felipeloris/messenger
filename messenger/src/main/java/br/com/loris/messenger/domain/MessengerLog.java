package br.com.loris.messenger.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MessengerLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private Date date;
	private MessengerLogOperation operation; 
	private String ip;
	
	public MessengerLog(long id, Date date, MessengerLogOperation operation, String ip) {
		super();
		
		this.id = id;
		this.date = date;
		this.operation = operation;
		this.ip = ip;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
	public MessengerLogOperation getOperation() {
		return operation;
	}
	
	public void setOperation(MessengerLogOperation operation) {
		this.operation = operation;
	}
	
	public String getIp() {
		return ip;
	}
	
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(date, id, operation);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof MessengerLog)) {
			return false;
		}
		MessengerLog other = (MessengerLog) obj;
		return Objects.equals(date, other.date) && Objects.equals(id, other.id) && operation == other.operation;
	}
}
