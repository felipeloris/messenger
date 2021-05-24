package br.com.loris.messenger.database.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;

import br.com.loris.messenger.interfaces.domain.Message;
import br.com.loris.messenger.interfaces.domain.MessagePerson;

@Entity
@Table(name = "message")
public class DbMessage implements Message {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "msg_title", nullable = false, length = 100)
	private String title;
	@Column(name = "msg_text", nullable = false, length = 2000)
	private String text;
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant inclusion;
	//@Column(name = "from_person_id", nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private DbMessagePerson from;
	@OneToMany(mappedBy="message", cascade = CascadeType.ALL)
	private Set<DbMessagePerson> to = new HashSet<>();
	private Boolean loaded;

	public DbMessage() {
	}

	public DbMessage(Long id, String title, String text, Instant inclusion, MessagePerson from,
			Set<MessagePerson> to, Boolean loaded) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.inclusion = inclusion;
		this.from = (DbMessagePerson)from;
		this.to = to.stream().map(x -> (DbMessagePerson)x).collect(Collectors.toSet());
		this.loaded = loaded;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
	public Instant getInclusion() {
		return inclusion;
	}
	
	public void setInclusion(Instant inclusion) {
		this.inclusion = inclusion;
	}

	public MessagePerson getFrom() {
		return from;
	}
	
	public void setFrom(MessagePerson from) {
		this.from = (DbMessagePerson) from;
	}

	public Set<MessagePerson> getTo() {
		return to.stream().map(x -> (MessagePerson)x).collect(Collectors.toSet());
	}
	
	public Boolean getLoaded() {
		return loaded;
	}

	public void setLoaded(Boolean loaded) {
		this.loaded = loaded;
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
		if (!(obj instanceof DbMessage)) {
			return false;
		}
		DbMessage other = (DbMessage) obj;
		return Objects.equals(id, other.id);
	}
}
