package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Comment;
import com.example.istg.repos.CommentRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;

	@Override
	public List<Comment> getAllComments() {
		return repo.findAll();
	}

	@Override
	public Comment getComment(Long id) {
		Comment c = repo.findById(id).orElseThrow();
		return c;
	}

	@Override
	public Comment createComment(Comment comment) {
		comment = repo.save(comment);
		return comment;
	}

	@Override
	public Comment updateComment(Comment comment) {
		if (repo.existsById(comment.getId())) {
			comment = repo.save(comment);
			return comment;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteComment(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

}
