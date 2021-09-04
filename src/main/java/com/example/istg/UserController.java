package com.example.istg;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import com.example.istg.commons.ChatCred;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
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

import com.example.istg.commons.User;
import com.example.istg.exceptions.DuplicatedUsernameOrEmailException;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	// get all
	@GetMapping("/all")
	public List<User> getAllUsers() {

		return service.getAllUsers();
	}

	@GetMapping("/login")
	public ResponseEntity<Object> login() {
		return ResponseEntity.ok().build();
	}

	@GetMapping("/current")
	public ResponseEntity<User> getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String name = authentication.getName();
		if ("anonymousUser".equals(name)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User user = service.getUserByUsername(name);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(user);
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<User> getUser(@PathVariable Long id) {
		try {
			User u = service.getUser(id);
			return ResponseEntity.ok(u);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}
	}

	// create new user
	@PostMapping("/create")
	public ResponseEntity<User> createUser(@RequestBody @Valid User user) throws IOException {
		try {
			return ResponseEntity.ok(service.createUser(user));
		} catch (DuplicatedUsernameOrEmailException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).build();
		}
	}

	@GetMapping("/rcc")
	public ResponseEntity<ChatCred> requestChatCred() throws IOException {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String name = authentication.getName();
		if ("anonymousUser".equals(name)) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		User user = service.getUserByUsername(name);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}
		ChatCred chatCred = user.getChatCred();
		if (chatCred == null) {
			chatCred = service.createChatCred(user);
		}
		return ResponseEntity.ok(chatCred);
	}

	// TODO handle invalid User
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

	// update user
	@PutMapping("/update")
	// Using the user id in request body instead of path variable
	public ResponseEntity<User> updateUser(@RequestBody @Valid User userDetail) {
		try {
			User user = service.updateUser(userDetail);
			return ResponseEntity.ok(user);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}

	}

	// delete user
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteUser(@PathVariable Long id) {
		try {
			service.deleteUser(id);
			return ResponseEntity.ok(id);
		} catch (NoSuchElementException e) {
			return ResponseEntity.notFound().build();
		}

	}

}
