package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.UserBlocking;
import com.example.istg.services.UserBlockingService;

@RestController
@RequestMapping("/api/userblocking")
public class UserBlockingController {
	@Autowired
	private UserBlockingService service;

	// get all
	@GetMapping("/all")
	public List<UserBlocking> getAllUserBlocking(Model model) {
		return service.getAllUserBlockings();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<UserBlocking> getUserBlocking(@PathVariable Long id) {
		try {
			UserBlocking blocking = service.getUserBlocking(id);
			return ok(blocking);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}

	// create new user blocking
	@PostMapping("/create")
	public UserBlocking createUserBlocking(@RequestBody UserBlocking user) {
		user = service.createUserBlocking(user);
		return user;
	}

	// update user blocking
	@PutMapping("/update")
	public ResponseEntity<UserBlocking> updateUserBlocking(@RequestBody UserBlocking blocking) {
		try {
			blocking = service.updateUserBlocking(blocking);
			return ok(blocking);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete user blocking
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteUserBlocking(@PathVariable Long id) {
		try {
			service.deleteUserBlocking(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}
}
