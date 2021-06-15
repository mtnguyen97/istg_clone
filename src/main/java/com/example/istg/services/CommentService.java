package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;
import com.example.istg.commons.User;

public interface CommentService {

	List<Comment> getAllComments();

	List<Comment> getAllComments(Post post);

	Comment getComment(Long id);

	Comment createComment(Comment comment, User user, Long postId);

	Comment updateComment(Long id, Comment comment, User user);

	void deleteComment(Long id, User user);

}
