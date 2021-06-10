package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.istg.commons.Post;
import com.example.istg.commons.User;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {

	@Transactional
	long deleteByIdAndPostedBy(Long id, User postedBy);

}
