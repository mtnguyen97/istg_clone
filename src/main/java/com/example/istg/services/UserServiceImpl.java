package com.example.istg.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.User;
import com.example.istg.exceptions.DuplicatedUsernameOrEmailException;
import com.example.istg.repos.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private @Autowired UserRepository repo;

	@Override
	public User findByUsernameAndPassword(String username, String password) {
		return repo.findByUsernameAndPassword(username, password);
	}

	@Override
	public List<User> getAllUsers() {
		return repo.findAll().stream().map((u) -> {
			// TODO hide users password
			u.setPassword("");
			return u;
		}).collect(Collectors.toList());
	}

	@Override
	public User getUser(Long id) {
		User u = repo.findById(id).orElseThrow();
		// TODO hide user password
		u.setPassword("");
		return u;
	}

	@Override
	public User createUser(User u) throws DuplicatedUsernameOrEmailException {
		if (repo.existsByEmail(u.getEmail()) || repo.existsByUsername(u.getUsername())) {
			throw new DuplicatedUsernameOrEmailException();
		}
		u.setCreatedAt(new Date());
		u = repo.save(u);
		return u;
	}

	@Override
	public User updateUser(User u) {
		if (repo.existsById(u.getId())) {
			u = repo.save(u);
			return u;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteUser(Long id) {
		if (!repo.existsById(id)) {
			throw new NoSuchElementException();
		}
		repo.deleteById(id);
	}

	@Override
	public User getUserByUsername(String username) {
		User user = repo.findByUsername(username);
		if (user != null) {
			user.setPassword(null);
		}
		return user;
	}

	@Override
	public boolean isEmailExisted(String email) {
		return repo.existsByEmail(email);
	}

}
