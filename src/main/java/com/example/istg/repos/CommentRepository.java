package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

}
