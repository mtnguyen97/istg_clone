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

import com.example.istg.commons.PostLiking;
import com.example.istg.services.PostLikingService;

@RestController
@RequestMapping("/api/postliking")
public class PostLikingController {
	@Autowired
	private PostLikingService service;

	// get all
	@GetMapping("/all")
	public List<PostLiking> getAllPostLinking() {
		return service.getAllPostLikings();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<PostLiking> getPostLinking(@PathVariable Long id) {
		try {
			PostLiking l = service.getPostLiking(id);
			return ok(l);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new post
	@PostMapping("/create")
	public PostLiking createPostLinking(@RequestBody PostLiking post) {
		return service.createPostLiking(post);
	}

	// update post
	@PutMapping("/update")
	public ResponseEntity<PostLiking> updatePostLinking(@RequestBody PostLiking postLinkingDetail) {
		try {
			postLinkingDetail = service.updatePostLiking(postLinkingDetail);
			return ok(postLinkingDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete post
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletePostLinking(@PathVariable Long id) {
		try {
			service.deletePostLiking(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
}
