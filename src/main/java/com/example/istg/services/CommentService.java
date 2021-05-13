package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Comment;

public interface CommentService {
	
	List<Comment> getAllComments();
	
	Comment getComment(Long id);
	
	Comment createComment(Comment comment);
	
	Comment updateComment(Comment comment);
	
	void deleteComment(Long id);

}
