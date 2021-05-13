package com.example.istg;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.istg.commons.Story;
import com.example.istg.repos.StoryRepository;

@RestController
@RequestMapping("/api")
public class StoryController {

	@Autowired
	private StoryRepository storyRepository;

	// get all
	@GetMapping("/stories")
	public ArrayList<Story> getAllStory(Model model) {

		return (ArrayList<Story>) storyRepository.findAll();
	}

	// get by id
	@GetMapping("story/{id}")
	public Story getStory(@PathVariable Long id) {
		return storyRepository.findById(id).get();
	}

	// create new story
	@PostMapping("/story/create")
	public Story createStory(@RequestBody Story story) {
		return storyRepository.save(story);
	}

	// update story
	@PutMapping("/story/update/{id}")
	public ResponseEntity<Story> updateStory(@PathVariable Long id, @RequestBody Story storyDetail) {

		Story story = storyRepository.findById(id).get();
		if (story != null) {
			story.setDeletedAt(storyDetail.getDeletedAt());
			story.setImage(storyDetail.getImage());
			story.setPostedBy(storyDetail.getPostedBy());
			story.setPublicStory(storyDetail.isPublicStory());
		

			final Story updateStory = storyRepository.save(story);
			return ResponseEntity.ok(updateStory);
		}
		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();

	}

	// delete story
	@RequestMapping(value = "story/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Long> deleteStory(@PathVariable Long id) {

		Story story = storyRepository.findById(id).get();

		if (story != null) {
			storyRepository.delete(story);
			return new ResponseEntity<>(HttpStatus.OK);
		}

		return new ResponseEntity<>(HttpStatus.NOT_FOUND);

	}
}
