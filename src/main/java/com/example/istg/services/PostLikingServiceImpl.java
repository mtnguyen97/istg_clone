package com.example.istg.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;
import com.example.istg.commons.User;
import com.example.istg.repos.PostLikingRepository;
import com.example.istg.repos.PostRepository;

@Service
public class PostLikingServiceImpl implements PostLikingService {

	@Autowired
	private PostLikingRepository repo;
	@Autowired
	private PostRepository postRepo;

	@Override
	public List<PostLiking> getAllPostLikings() {
		return repo.findAll();
	}

	@Override
	public PostLiking getPostLiking(Long id) {
		return repo.findById(id).get();
	}

	@Override
	public PostLiking createPostLiking(User user, Long postId) {
		Post post = postRepo.findById(postId).get();
		PostLiking postLiking = repo.findByUserAndPost(user, post);
		if (postLiking != null) {
			return postLiking;
		}
		postLiking = new PostLiking();
		postLiking.setPost(post);
		postLiking.setLikingAt(new Date());
		postLiking.setUser(user);
		return repo.save(postLiking);
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
	public void deletePostLiking(Long id, User user) {
		if (repo.existsByIdAndUser(id, user)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

	public void deleteAll(Iterable<PostLiking> likings) {
		repo.deleteAll(likings);
	}

	@Override
	public List<PostLiking> getAllPostLikings(Post post) {
		return repo.findByPost(post);
	}

}
