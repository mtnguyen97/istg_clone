package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.Message;
import com.example.istg.services.MessageService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService service;

	// get all
	@GetMapping("/all")
	public List<Message> getAllMessages() {
		return service.getAllMessages();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<Message> getMessage(@PathVariable Long id) {
		try {
			Message m = service.getMessage(id);
			return ok(m);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new msg
	@PostMapping("/create")
	public Message createMessage(@RequestBody Message msg) {
		msg = service.createMessage(msg);
		return msg;
	}

	// update msg
	@PutMapping("/update")
	public ResponseEntity<Message> updateMessage(@RequestBody Message msgDetail) {
		try {
			msgDetail = service.updateMessage(msgDetail);
			return ok(msgDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete msg
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteMessage(@PathVariable Long id) {
		try {
			service.deleteMessage(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}
}
