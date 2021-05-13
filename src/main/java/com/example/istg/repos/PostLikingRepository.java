package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.PostLiking;

@Repository
public interface PostLikingRepository extends JpaRepository<PostLiking, Long> {

}
