package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Following;

public interface FollowingService {
	List<Following> getAllFollowings();
	
	Following getFollowing(Long id);
	
	Following createFollowing(Following following);
	
	Following updateFollowing(Following following);
	
	void deleteFollowing(Long id);
}
