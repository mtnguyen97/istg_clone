package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Service;

import com.example.istg.commons.PostLiking;
import com.example.istg.repos.PostLikingRepository;

@Service
public class PostLikingServiceImpl implements PostLikingService {

	private PostLikingRepository repo;

	@Override
	public List<PostLiking> getAllPostLikings() {
		return repo.findAll();
	}

	@Override
	public PostLiking getPostLiking(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public PostLiking createPostLiking(PostLiking postLiking) {
		postLiking = repo.save(postLiking);
		return postLiking;
	}

	@Override
	public PostLiking updatePostLiking(PostLiking postLiking) {
		if (repo.existsById(postLiking.getId())) {
			postLiking = repo.save(postLiking);
			return postLiking;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deletePostLiking(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

}
