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

import com.example.istg.commons.Post;
import com.example.istg.services.PostService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService service;

	// get all
	@GetMapping("/all")
	public List<Post> getAllPost(Model model) {
		return service.getAllPosts();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<Post> getPost(@PathVariable Long id) {
		try {
			Post p = service.getPost(id);
			return ok(p);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new post
	@PostMapping("/create")
	public Post createPost(@RequestBody Post post) {
		post = service.createPost(post);
		return post;
	}

	// update post
	@PutMapping("/update")
	public ResponseEntity<Post> updatePost(@RequestBody Post postDetail) {
		try {
			postDetail = service.updatePost(postDetail);
			return ok(postDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete post
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deletePost(@PathVariable Long id) {
		try {
			service.deletePost(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
}
