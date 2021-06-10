package com.example.istg.services;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.istg.commons.Message;
import com.example.istg.commons.User;
import com.example.istg.repos.MessageRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	private MessageRepository repo;

	@Override
	public List<Message> getAllMessages() {
		return repo.findAll();
	}

	@Override
	public Message getMessage(Long id) {
		return repo.findById(id).orElseThrow();
	}

	@Override
	public Message createMessage(Message m, User sender, User receiver) {
		m.setId(null);
		m.setSender(sender);
		m.setReceiver(receiver);
		m.setCreatedAt(new Date());
		m = repo.save(m);
		return m;
	}

	@Override
	public Message updateMessage(Message m) {
		if (repo.existsById(m.getId())) {
			m = repo.save(m);
			return m;
		}
		throw new NoSuchElementException();
	}

	@Override
	public void deleteMessage(Long id) {
		if (repo.existsById(id)) {
			repo.deleteById(id);
			return;
		}
		throw new NoSuchElementException();
	}

}
