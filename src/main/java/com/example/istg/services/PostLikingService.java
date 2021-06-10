package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Post;
import com.example.istg.commons.PostLiking;

public interface PostLikingService {

	List<PostLiking> getAllPostLikings();

	List<PostLiking> getAllPostLikings(Post post);

	PostLiking getPostLiking(Long id);

	PostLiking createPostLiking(PostLiking postLiking);

	PostLiking updatePostLiking(PostLiking postLiking);

	void deletePostLiking(Long id);

}
