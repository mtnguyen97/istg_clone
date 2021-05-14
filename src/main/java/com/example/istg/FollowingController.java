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

import com.example.istg.commons.Following;
import com.example.istg.services.FollowingService;

@RestController
@RequestMapping("/api/following")
public class FollowingController {

	@Autowired
	private FollowingService service;

	// get all
	@GetMapping("/all")
	public List<Following> getAllFollowing(Model model) {
		return service.getAllFollowings();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<Following>  getFollowing(@PathVariable Long id) {
		try {
			Following f = service.getFollowing(id);
			return ok(f);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new cmt
	@PostMapping("/create")
	public Following createFollowing(@RequestBody Following flw) {
		return service.createFollowing(flw);
	}

	// update flw
	@PutMapping("/update")
	public ResponseEntity<Following> updateFollowing(@RequestBody Following flwDetail) {
		try {
			flwDetail = service.updateFollowing(flwDetail);
			return ok(flwDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}

	// delete flw
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteFollowing(@PathVariable Long id) {
		try {
			service.deleteFollowing(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}


	}
	
	
}
