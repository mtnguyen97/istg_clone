package com.example.istg.services;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

import com.example.istg.commons.ChatCred;
import com.example.istg.commons.User;
import com.example.istg.exceptions.DuplicatedUsernameOrEmailException;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface UserService {

	User findByUsernameAndPassword(String username, String password);

	List<User> getAllUsers();

	User getUser(Long id);

	User createUser(User u) throws DuplicatedUsernameOrEmailException;

	User updateUser(User u);

	void deleteUser(Long id);

	User getUserByUsername(String username);

	boolean isEmailExisted(String email);

	ChatCred createChatCred(User user) throws IOException;

}
