package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Post;

public interface PostService {

	List<Post> getAllPosts();

	Post getPost(Long id);

	Post createPost(Post p);

	Post updatePost(Post p);

	void deletePost(Long id);

}
