package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

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
import org.springframework.web.bind.annotation.DeleteMapping;
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

import com.example.istg.commons.Post;
import com.example.istg.commons.User;
import com.example.istg.dto.IdAndCreatedAtAndCreatedBy;
import com.example.istg.services.PostService;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/post")
public class PostController {
	@Autowired
	private PostService service;

	@Autowired
	private UserService userService;

	// get all
	@GetMapping("/all")
	public List<Post> getAllPost() {
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
	public ResponseEntity<Object> createPost(@RequestBody @Valid Post post) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		post = service.createPost(post, user);
		if (post == null) {
			return ResponseEntity.badRequest().build();
		}
		IdAndCreatedAtAndCreatedBy response = new IdAndCreatedAtAndCreatedBy();
		response.setId(post.getId());
		response.setCreatedAt(post.getCreatedAt());
		response.setCreatedBy(post.getPostedBy().getId());
		return ResponseEntity.ok(response);
	}

	// update post
	@PutMapping("/update")
	public ResponseEntity<Post> updatePost(@RequestBody @Valid Post postDetail) {
		try {
			postDetail = service.updatePost(postDetail);
			return ok(postDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// TODO handle invalid Post
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

	// delete post
	@PostMapping("/delete/{id}")
	public ResponseEntity<Long> deletePost(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			service.deletePost(id, user);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
}
