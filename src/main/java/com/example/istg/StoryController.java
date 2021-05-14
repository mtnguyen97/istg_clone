package com.example.istg;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.Story;
import com.example.istg.services.StoryService;

@RestController
@RequestMapping("/api/story")
public class StoryController {

	@Autowired
	private StoryService service;

	// get all
	@GetMapping("/all")
	public List<Story> getAllStories() {
		return service.getAllStories();
	}

	// get by id
	@GetMapping("/id/{id}")
	public ResponseEntity<Story> getStory(@PathVariable Long id) {
		try {
			Story s = service.getStory(id);
			return ok(s);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// create new story
	@PostMapping("/story/create")
	public Story createStory(@RequestBody Story story) {
		story = service.createStory(story);
		return story;
	}

	// update story
	@PutMapping("/update")
	public ResponseEntity<Story> updateStory(@RequestBody Story story) {
		try {
			story = service.updateStory(story);
			return ok(story);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}

	// delete story
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteStory(@PathVariable Long id) {
		try {
			service.deleteStory(id);
			return ok(id);
		} catch (NoSuchElementException e) {
			return notFound().build();
		}
	}
}
