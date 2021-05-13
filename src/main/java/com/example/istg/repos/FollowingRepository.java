package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Following;

@Repository
public interface FollowingRepository extends JpaRepository<Following, Long> {

}
