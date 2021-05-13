package com.example.istg.services;

import java.util.List;

import com.example.istg.commons.Message;

public interface MessageService {

	List<Message> getAllMessages();

	Message getMessage(Long id);

	Message createMessage(Message m);

	Message updateMessage(Message m);

	void deleteMessage(Long id);

}
