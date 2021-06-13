package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;
import com.example.istg.commons.User;

public interface PostLikingService {

	List<PostLiking> getAllPostLikings();

	List<PostLiking> getAllPostLikings(Post post);

	PostLiking getPostLiking(Long id);

	PostLiking createPostLiking(User user, Long postId);

	PostLiking updatePostLiking(PostLiking postLiking);

	void deletePostLiking(Long id);

}
