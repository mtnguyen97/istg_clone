package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Post;
import com.example.istg.commons.User;

public interface PostService {

	List<Post> getAllPosts();

	Post getPost(Long id);

	Post createPost(Post p, User user);

	Post updatePost(Post p);

	void deletePost(Long id);

}
