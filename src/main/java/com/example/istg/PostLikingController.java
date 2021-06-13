package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;
import com.example.istg.commons.User;
import com.example.istg.dto.IdAndCreatedAt;
import com.example.istg.services.PostLikingService;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/postliking")
public class PostLikingController {
	@Autowired
	private PostLikingService service;
	@Autowired
	private UserService userService;

	// get all
	@GetMapping("/all/post/{id}")
	public List<PostLiking> getAllPostLinking(@PathVariable Long id) {
		Post p = new Post();
		p.setId(id);
		return service.getAllPostLikings(p);
	}

	// get all
	@GetMapping("/all/")
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
	@PostMapping("/create/{postId}")
	public ResponseEntity<IdAndCreatedAt> createPostLinking(@PathVariable Long postId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			PostLiking postLiking = service.createPostLiking(user, postId);
			IdAndCreatedAt r = new IdAndCreatedAt();
			r.setId(postLiking.getId());
			r.setCreatedAt(postLiking.getLikingAt());
			return ok(r);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

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
	@PostMapping("/delete/{id}")
	public ResponseEntity<Long> deletePostLinking(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			service.deletePostLiking(id, user);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
}
