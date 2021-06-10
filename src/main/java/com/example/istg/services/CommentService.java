package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;

public interface CommentService {

	List<Comment> getAllComments();

	List<Comment> getAllComments(Post post);

	Comment getComment(Long id);

	Comment createComment(Comment comment);

	Comment updateComment(Comment comment);

	void deleteComment(Long id);

}
