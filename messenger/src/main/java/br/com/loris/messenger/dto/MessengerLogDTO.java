package br.com.loris.messenger.dto;

import java.util.Date;

import br.com.loris.messenger.domain.MessengerLog;
import br.com.loris.messenger.domain.MessengerLogOperation;

public class MessengerLogDTO {
	private Date date;
	private int operation;
	private String ip;
	
	public MessengerLogDTO() {
		
	}
	
	public MessengerLogDTO(Date date, int operation, String ip, String message) {
		super();
		this.date = date;
		this.operation = operation;
		this.ip = ip;
	}
	
	public MessengerLogDTO(MessengerLog obj) {
		super();
		this.date = obj.getDate();
		this.operation = obj.getOperation().getCode();
		this.ip = obj.getIp();
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public int getOperation() {
		return operation;
	}
	
	public void setOperation(int operation) {
		this.operation = operation;
	} 
	
	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}
}
