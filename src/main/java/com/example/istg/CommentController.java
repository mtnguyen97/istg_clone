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

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;
import com.example.istg.services.CommentService;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	@Autowired
	private CommentService service;

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
	@PostMapping("/comment/create")
	public Comment createComment(@RequestBody Comment comment) {
		return service.createComment(comment);
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
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteComment(@PathVariable Long id) {
		try {
			service.deleteComment(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}

	}
}
