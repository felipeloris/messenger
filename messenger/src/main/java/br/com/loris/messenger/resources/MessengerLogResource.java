package br.com.loris.messenger.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import br.com.loris.messenger.domain.MessengerLog;
import br.com.loris.messenger.domain.MessengerLogOperation;
import br.com.loris.messenger.dto.MessengerLogDTO;
import br.com.loris.messenger.services.MessengerLogService;

@RestController
@RequestMapping(value="/messengerlog")
public class MessengerLogResource {
	@Autowired
	private MessengerLogService service;
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<List<MessengerLogDTO>> findAll() {
		List<MessengerLog> list = service.findAll();
		List<MessengerLogDTO> listDto = list.stream().map(x -> new MessengerLogDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto);
	}

	@RequestMapping(value="/{operation}", method=RequestMethod.GET)
 	public ResponseEntity<MessengerLogDTO> findByOperation(@PathVariable int operation) {
		MessengerLog obj = service.findByOperation(MessengerLogOperation.valueOf(operation));
		return ResponseEntity.ok().body(new MessengerLogDTO(obj));
	}

	/*
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
 	public ResponseEntity<MessengerLogDTO> findById(@PathVariable long id) {
		MessengerLog obj = service.findById(id);
		return ResponseEntity.ok().body(new MessengerLogDTO(obj));
	}
	
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Void> insert(@RequestBody MessengerLogDTO objDto) {
		MessengerLog obj = service.fromDTO(objDto);
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
 	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
 	public ResponseEntity<Void> update(@RequestBody MessengerLogDTO objDto, @PathVariable String id) {
		MessengerLog obj = service.fromDTO(objDto);
		obj.setId(id);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	*/
}
