package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.FriendRequesting;
import com.example.istg.repos.FriendRequestingRepository;

@Service
public class FriendRequestServiceImpl implements FriendRequestService {
	
	@Autowired
	private FriendRequestingRepository repo;

	@Override
	public List<FriendRequesting> getAllFriendRequestings() {
		return repo.findAll();
	}

	@Override
	public FriendRequesting getFriendRequesting(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public FriendRequesting createFriendRequesting(FriendRequesting requesting) {
		requesting = repo.save(requesting);
		return requesting;
	}

	@Override
	public FriendRequesting updateFriendRequesting(FriendRequesting requesting) {
		if (repo.existsById(requesting.getId())) {
			requesting = repo.save(requesting);
			return requesting;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteFriendRequesting(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}
	
	

}
