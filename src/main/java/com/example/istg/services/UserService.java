package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.User;

public interface UserService {

	User findByUsernameAndPassword(String username, String password);

	List<User> getAllUsers();

	User getUser(Long id);

	User createUser(User u);

	User updateUser(User u);
	
	void deleteUser(Long id);

}
