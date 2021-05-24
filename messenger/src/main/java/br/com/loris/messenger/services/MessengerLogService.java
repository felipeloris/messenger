package br.com.loris.messenger.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.loris.messenger.domain.MessengerLog;
import br.com.loris.messenger.domain.MessengerLogOperation;
import br.com.loris.messenger.dto.MessengerLogDTO;
import br.com.loris.messenger.exceptions.ObjectNotFoundException;
import br.com.loris.messenger.repository.MessengerLogRepository;

@Service
public class MessengerLogService {
	@Autowired
	private MessengerLogRepository repo;
	
	public List<MessengerLog> findAll() {
		return repo.findAll();
	}

	public MessengerLog findById(long id) {
		Optional<MessengerLog> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
	}
	
	public MessengerLog findByOperation(MessengerLogOperation operation) {
		return null;
	}
	
	public MessengerLog findByDate(Date startDate, Date endDate) {
		return null;
	}

	public MessengerLog insert(MessengerLog obj) {
		return repo.insert(obj);
	}

	public void delete(long id) {
		findById(id);
		repo.deleteById(id);
	}

	public MessengerLog fromDTO(MessengerLogDTO objDto) {
		return new MessengerLog(
				0, 
				objDto.getDate(), 
				MessengerLogOperation.valueOf(objDto.getOperation()),
				objDto.getIp());
	}
}
