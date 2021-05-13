package com.example.istg;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import com.example.istg.repos.UserBlockingRepository;

@RestController
@RequestMapping("/api")
public class UserBlockingController {
	@Autowired
	private UserBlockingRepository userBlockingRepository;

	// get all
	@GetMapping("/userblockings")
	public ArrayList<UserBlocking> getAllUserBlocking(Model model) {

		return (ArrayList<UserBlocking>) userBlockingRepository.findAll();
	}

	// get by id
	@GetMapping("userblocking/{id}")
	public UserBlocking getUserBlocking(@PathVariable Long id) {
		return userBlockingRepository.findById(id).get();
	}

	// create new user blocking
	@PostMapping("/userblocking/create")
	public UserBlocking createUserBlocking(@RequestBody UserBlocking user) {
		return userBlockingRepository.save(user);
	}

	// update user blocking
	@PutMapping("/userblocking/update/{id}")
	public ResponseEntity<UserBlocking> updateUserBlocking(@PathVariable Long id, @RequestBody UserBlocking userDetail) {

		UserBlocking user = userBlockingRepository.findById(id).get();
		if (user != null) {
			user.setBlockee(userDetail.getBlockee());
			user.setBlocker(userDetail.getBlocker());
			user.setBlockingAt(userDetail.getBlockingAt());
			user.setUnblockingAt(userDetail.getUnblockingAt());

			final UserBlocking updateUser = userBlockingRepository.save(user);
			return ResponseEntity.ok(updateUser);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	// delete user blocking
	@RequestMapping(value = "userblocking/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteUserBlocking(@PathVariable Long id) {

		UserBlocking user = userBlockingRepository.findById(id).get();

		if (user != null) {
			userBlockingRepository.delete(user);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
