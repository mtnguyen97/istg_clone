package com.example.istg.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.istg.commons.Story;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {

}
