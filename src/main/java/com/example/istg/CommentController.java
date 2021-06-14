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

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;
import com.example.istg.commons.User;
import com.example.istg.dto.IdAndCreatedAt;
import com.example.istg.services.CommentService;
import com.example.istg.services.UserService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService service;
	@Autowired
	private UserService userService;

	// get all
	@GetMapping("/all")
	public List<Comment> getAllComment(Model model) {
		return service.getAllComments();
	}

	// get all
	@GetMapping("/all/post/{id}")
	public List<Comment> getAllPostLinking(@PathVariable Long id) {
		Post p = new Post();
		p.setId(id);
		return service.getAllComments(p);
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<Comment> getComment(@PathVariable Long id) {
		try {
			Comment c = service.getComment(id);
			return ok(c);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new cmt
	@PostMapping("/create/{postId}")
	public ResponseEntity<IdAndCreatedAt> createComment(@RequestBody @Valid Comment comment,
			@PathVariable Long postId) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			comment = service.createComment(comment, user, postId);
			IdAndCreatedAt r = new IdAndCreatedAt();
			r.setId(comment.getId());
			r.setCreatedAt(comment.getCreatedAt());
			return ok(r);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// update cmt
	@PutMapping("/update")
	public ResponseEntity<Comment> updateComment(@RequestBody Comment commentDetail) {
		try {
			commentDetail = service.updateComment(commentDetail);
			return ok(commentDetail);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}

	// delete cmt
	@PostMapping("/delete/{id}")
	public ResponseEntity<Long> deleteComment(@PathVariable Long id) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.getUserByUsername(authentication.getName());
		if (user == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		try {
			service.deleteComment(id, user);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
	
	// TODO handle invalid comment
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
}
