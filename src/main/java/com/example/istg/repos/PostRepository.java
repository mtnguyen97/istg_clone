package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Post;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long>{

}
