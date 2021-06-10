package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Message;
import com.example.istg.commons.User;

public interface MessageService {

	List<Message> getAllMessages();

	Message getMessage(Long id);

	Message createMessage(Message m, User sender, User receiver);

	Message updateMessage(Message m);

	void deleteMessage(Long id);

}
