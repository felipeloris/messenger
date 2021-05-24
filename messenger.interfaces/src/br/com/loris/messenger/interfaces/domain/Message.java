package br.com.loris.messenger.interfaces.domain;

import java.time.Instant;
import java.util.Set;

public interface Message {
    public Long getId();
    
    public void setId(Long id);
    
    public String getTitle();
    
    public void setTitle(String title);
    
    public String getText();
    
    public void setText(String text);
    
    public Instant getInclusion();
    
    public void setInclusion(Instant date);
    
    public MessagePerson getFrom();
    
    public void setFrom(MessagePerson from);
    
    public Set<MessagePerson> getTo();
}
