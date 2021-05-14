package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.UserBlocking;

public interface UserBlockingService {

	List<UserBlocking> getAllUserBlockings();

	UserBlocking getUserBlocking(Long id);

	void deleteUserBlocking(Long id);

	UserBlocking createUserBlocking(UserBlocking blocking);

	UserBlocking updateUserBlocking(UserBlocking blocking);

}
