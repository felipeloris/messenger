package br.com.loris.messenger.database.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.loris.messenger.database.entities.DbMessage;
import br.com.loris.messenger.database.exceptions.DatabaseException;
import br.com.loris.messenger.database.exceptions.ResourceNotFoundException;
import br.com.loris.messenger.database.repositories.DbMessageRepository;
import br.com.loris.messenger.interfaces.domain.Message;
import br.com.loris.messenger.interfaces.services.MessengerService;

@Service
public class DbMessageService implements MessengerService {
	@Autowired
	private DbMessageRepository repository;
	
	public List<DbMessage> findAll() {
		return repository.findAll();
	}
	
	public DbMessage findById(Long id) {
		Optional<DbMessage> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
	
	public DbMessage SetLoaded(Long id, Message obj) {
		try {
			DbMessage dbObj = repository.getById(id);
			dbObj.setLoaded(true);
			
			return repository.save(dbObj);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}	
	}

	@Override
	public Message NextMessage() {
		return repository.findFirstUnload();
	}

	@Override
	public Message SaveMessage(Message message) {
		DbMessage dbObj = (DbMessage)message;
		dbObj.setLoaded(false);
		return repository.save(dbObj);		
	}
}
