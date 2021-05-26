package com.example.istg;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.User;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserService service;

	// get all
	@GetMapping("/all")
	public List<User> getAllUser(Model model) {

		return service.getAllUsers();
	}

	@GetMapping("/current")
	public ResponseEntity<User> getCurrentUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		String name = authentication.getName();
		if (name == "anonymousUser") {
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
	public User createUser(@RequestBody User user) {
		return service.createUser(user);
	}

	// update user
	@PutMapping("/update")
	// Using the user id in request body instead of path variable
	public ResponseEntity<User> updateUser(@RequestBody User userDetail) {
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
