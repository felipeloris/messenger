package br.com.loris.messenger.resources;

import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.loris.messenger.database.services.DbMessageService;
import br.com.loris.messenger.domain.MessengerLog;
import br.com.loris.messenger.domain.MessengerLogOperation;
import br.com.loris.messenger.interfaces.domain.Message;
import br.com.loris.messenger.services.MessengerLogService;

@RestController
@RequestMapping(value="/message")
public class MessageResource {
	@Autowired 
	private HttpServletRequest request;
	@Autowired
	private DbMessageService service;
	@Autowired
	private MessengerLogService serviceLog;
	
	@RequestMapping(method=RequestMethod.POST)
 	public ResponseEntity<Message> SaveMessage(@RequestBody Message message) {
		serviceLog.insert(
				new MessengerLog(
						0, 
						Calendar.getInstance().getTime(), 
						MessengerLogOperation.INPUT, 
						request.getRemoteHost()));
		
		Message newMessage = service.SaveMessage(message);
		//List<MessengerLogDTO> listDto = list.stream().map(x -> new MessengerLogDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(newMessage);
	}
	
	@RequestMapping(method=RequestMethod.GET)
 	public ResponseEntity<Message> NextMessage() {
		serviceLog.insert(
				new MessengerLog(
						0, 
						Calendar.getInstance().getTime(), 
						MessengerLogOperation.OUTPUT, 
						request.getRemoteHost()));
		
		Message message = service.NextMessage();
		//List<MessengerLogDTO> listDto = list.stream().map(x -> new MessengerLogDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(message);
	}
}
