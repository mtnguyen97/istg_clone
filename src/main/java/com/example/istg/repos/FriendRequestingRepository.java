package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.FriendRequesting;

@Repository
public interface FriendRequestingRepository extends JpaRepository<FriendRequesting, Long> {

}
