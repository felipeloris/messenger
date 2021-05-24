package br.com.loris.messenger.database.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.loris.messenger.interfaces.domain.Message;
import br.com.loris.messenger.interfaces.domain.MessagePerson;

@Entity
@Table(name = "message_person")
public class DbMessagePerson implements MessagePerson, Serializable  {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne()
	private DbMessage message;
	@Column(nullable = false, length = 100)
	private String name;
	@Column(nullable = true, length = 100)
	private String email;
	@Column(nullable = true, length = 100)
	private String phoneNumber;
	
	public DbMessagePerson() {
		super();
	}

	public DbMessagePerson(Long id, Message message, String name, String email, String phoneNumber) {
		super();
		this.id = id;
		this.message = (DbMessage)message;
		this.name = name;
		this.email = email;
		this.phoneNumber = phoneNumber;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	@JsonIgnore
	public DbMessage getMessage() {
		return message;
	}

	public void setMessage(DbMessage message) {
		this.message = (DbMessage)message;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPhoneNumber() {
		return phoneNumber;
	}
	
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DbMessagePerson)) {
			return false;
		}
		DbMessagePerson other = (DbMessagePerson) obj;
		return Objects.equals(id, other.id);
	}
}
