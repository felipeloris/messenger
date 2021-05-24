package br.com.loris.messenger.interfaces.services;

import br.com.loris.messenger.interfaces.domain.Message;

public interface MessengerService {
	public Message NextMessage();
	public Message SaveMessage(Message message);
}
