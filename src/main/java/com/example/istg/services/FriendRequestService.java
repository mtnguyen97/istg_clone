package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.FriendRequesting;

public interface FriendRequestService {

	List<FriendRequesting> getAllFriendRequestings();

	FriendRequesting getFriendRequesting(Long id);

	FriendRequesting createFriendRequesting(FriendRequesting requesting);

	FriendRequesting updateFriendRequesting(FriendRequesting requesting);

	void deleteFriendRequesting(Long id);

}
