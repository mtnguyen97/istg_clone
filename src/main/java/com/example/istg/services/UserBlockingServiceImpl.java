package com.example.istg.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.UserBlocking;
import com.example.istg.repos.UserBlockingRepository;

@Service
public class UserBlockingServiceImpl implements UserBlockingService {

	@Autowired
	private UserBlockingRepository repo;

	@Override
	public List<UserBlocking> getAllUserBlockings() {
		return repo.findAll();
	}

	@Override
	public UserBlocking getUserBlocking(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public void deleteUserBlocking(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

	@Override
	public UserBlocking createUserBlocking(UserBlocking blocking) {
		return repo.save(blocking);
	}

	@Override
	public UserBlocking updateUserBlocking(UserBlocking blocking) {
		if (repo.existsById(blocking.getId())) {
			return repo.save(blocking);
		}
		throw new NoSuchElementException();
	}

}
