package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.istg.commons.Story;
import com.example.istg.repos.StoryRepository;

@Service
public class StoryServiceImpl implements StoryService {

	private StoryRepository repo;

	@Override
	public List<Story> getAllStories() {
		return repo.findAll();
	}

	@Override
	public Story getStory(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public Story createStory(Story story) {
		return repo.save(story);
	}

	@Override
	public Story updateStory(Story story) {
		if (repo.existsById(story.getId())) {
			story = repo.save(story);
			return story;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteStory(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

}
