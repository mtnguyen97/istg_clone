package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Story;

public interface StoryService {

	List<Story> getAllStories();

	Story getStory(Long id);

	Story createStory(Story story);

	Story updateStory(Story story);

	void deleteStory(Long id);

}
