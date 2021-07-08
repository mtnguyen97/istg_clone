package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Following;
import com.example.istg.repos.FollowingRepository;

@Service
public class FollowingServiceImpl implements FollowingService {

	@Autowired
	private FollowingRepository repo;

	@Override
	public List<Following> getAllFollowings() {
		return repo.findAll();
	}

	@Override
	public Following getFollowing(Long id) {
		Following f = repo.findById(id).get();
		return f;
	}

	@Override
	public Following createFollowing(Following following) {
		following = repo.save(following);
		return following;
	}

	@Override
	public Following updateFollowing(Following following) {
		if (repo.existsById(following.getId())) {
			following = repo.save(following);
			return following;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteFollowing(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

}
