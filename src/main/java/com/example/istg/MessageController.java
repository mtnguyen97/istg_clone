package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.ChatCred;
import com.example.istg.commons.Message;
import com.example.istg.commons.User;
import com.example.istg.dto.IdAndCreatedAt;
import com.example.istg.services.MessageService;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/message")
public class MessageController {

	@Autowired
	private MessageService service;

	@Autowired
	private UserService userService;

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
	@PostMapping("/create/{username}")
	public ResponseEntity<IdAndCreatedAt> createMessage(@RequestBody @Valid Message msg,
			@PathVariable String username) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User receiver = userService.getUserByUsername(username);
		if (receiver == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		if (user.getId().equals(receiver.getId())) {
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
		msg = service.createMessage(msg, user, receiver);
		IdAndCreatedAt response = new IdAndCreatedAt();
		response.setId(msg.getId());
		response.setCreatedAt(msg.getCreatedAt());
		return ResponseEntity.ok(response);
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

	// TODO handle invalid Message
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return errors;
	}

	@GetMapping("/cred")
	public ResponseEntity<ChatCred> getChadCred() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		if (user.getChatCred() != null) {
			return ok(user.getChatCred());
		}
		return notFound().build();
	}
	
}
