package com.example.istg.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Post;
import com.example.istg.commons.User;
import com.example.istg.repos.PostRepository;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository repo;

	@Override
	public List<Post> getAllPosts() {
		return repo.findAll();
	}

	@Override
	public Post getPost(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public Post createPost(Post p, User user) {
		p.setPostedBy(user);
		p.setCreatedAt(new Date());
		if (p.getImages().isEmpty()) {
			return null;
		}
		p.setId(null);
		p.setDeletedAt(null);
		p = repo.save(p);
		return p;
	}

	@Override
	public Post updatePost(Post p) {
		if (repo.existsById(p.getId())) {
			p = repo.save(p);
			return p;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deletePost(Long id, User user) {
		if (repo.deleteByIdAndPostedBy(id, user) > 0) {
			return;
		}
		throw new NoSuchElementException();
	}

}
