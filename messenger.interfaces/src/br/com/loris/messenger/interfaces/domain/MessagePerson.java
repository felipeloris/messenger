package br.com.loris.messenger.interfaces.domain;

public interface MessagePerson {
	public Long getId();
	
	public void setId(Long id);
	
	public String getName();
	
	public void setName(String name);
	
	public String getEmail();
	
	public void setEmail(String email);
	
	public String getPhoneNumber();
	
	public void setPhoneNumber(String phoneNumber);
}
