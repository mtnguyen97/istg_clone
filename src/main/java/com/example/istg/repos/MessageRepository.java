package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Message;

@Repository
public interface MessageRepository  extends JpaRepository<Message, Long>{

}
