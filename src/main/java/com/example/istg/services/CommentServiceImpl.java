package com.example.istg.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;
import com.example.istg.commons.User;
import com.example.istg.repos.CommentRepository;
import com.example.istg.repos.PostRepository;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentRepository repo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<Comment> getAllComments() {
		return repo.findAll();
	}

	@Override
	public Comment getComment(Long id) {
		Comment c = repo.findById(id).get();
		return c;
	}

	@Override
	public Comment createComment(Comment comment, User user, Long postId) {
		Post post = postRepo.findById(postId).get();
		comment.setId(null);
		comment.setCommentedBy(user);
		comment.setCreatedAt(new Date());
		comment.setDeletedAt(null);
		comment.setPost(post);
		comment = repo.save(comment);
		return comment;
	}

	@Override
	public Comment updateComment(Long id, Comment comment, User user) {
		Comment oldComment = repo.findById(id).orElse(null);
		if (oldComment == null || !oldComment.getCommentedBy().getId().equals(user.getId())) {
			return null;
		}
		oldComment.setContent(comment.getContent());
		oldComment.setUpdatedAt(new Date());
		oldComment = repo.save(oldComment);
		return oldComment;
	}

	@Override
	public void deleteComment(Long id, User user) {
		if (repo.existsByIdAndCommentedBy(id, user)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteAll(Iterable<Comment> comments) {
		repo.deleteAll(comments);
	}

	@Override
	public List<Comment> getAllComments(Post post) {
		return repo.findByPost(post);
	}

}
