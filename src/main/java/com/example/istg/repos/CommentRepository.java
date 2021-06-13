package com.example.istg.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Comment;
import com.example.istg.commons.Post;
import com.example.istg.commons.User;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findByPost(Post post);

	boolean existsByIdAndCommentedBy(Long id, User commentedBy);
}
