package com.example.istg.repos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;
import com.example.istg.commons.User;

@Repository
public interface PostLikingRepository extends JpaRepository<PostLiking, Long> {

	List<PostLiking> findByPost(Post post);

	PostLiking findByUserAndPost(User user, Post post);

	boolean existsByIdAndUser(Long id, User user);
}
