package com.codingdojo.BeltExam.services;

import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.codingdojo.BeltExam.models.Message;
import com.codingdojo.BeltExam.repositiories.MessageRepository;

@Service
public class MessageService {
    private MessageRepository messageRepository;
	
	public MessageService(MessageRepository messageRepository) {
		this.messageRepository = messageRepository;
	}
	
	public ArrayList<Message> all() {
		return (ArrayList<Message>) messageRepository.findAll();
	}
	
	public Message findOne(Long id) {
		return messageRepository.findById(id).get();
	}
	
	public void create(Message message) {
		messageRepository.save(message);
	}
	
	public void delete(Long id) {
		messageRepository.deleteById(id);
	}
	
	public void update(Message message) {
		messageRepository.save(message);
	}

}
