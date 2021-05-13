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

import com.example.istg.commons.FriendRequesting;
import com.example.istg.services.FriendRequestService;

@RestController
@RequestMapping("/api/friendrequest")
public class FriendRequestingController {
	@Autowired
	private FriendRequestService service;

	// get all
	@GetMapping("/all")
	public List<FriendRequesting> getAllFriendRequest() {
		return service.getAllFriendRequestings();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<FriendRequesting> getFriendRq(@PathVariable Long id) {
		try {
			FriendRequesting f = service.getFriendRequesting(id);
			return ok(f);

		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new friendRq
	@PostMapping("/create")
	public FriendRequesting createFriendRq(@RequestBody FriendRequesting friendRq) {
		return service.createFriendRequesting(friendRq);
	}

	// update friendRq
	@PutMapping("/update")
	public ResponseEntity<FriendRequesting> updateFriendRq(FriendRequesting friendRqDetail) {
		try {
			friendRqDetail = service.updateFriendRequesting(friendRqDetail);
			return ok(friendRqDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete friendRq
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteFollowing(@PathVariable Long id) {
		try {
			service.deleteFriendRequesting(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}
}
