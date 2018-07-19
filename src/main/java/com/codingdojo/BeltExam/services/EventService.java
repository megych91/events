package com.codingdojo.BeltExam.services;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import com.codingdojo.BeltExam.models.Event;
import com.codingdojo.BeltExam.models.User;
import com.codingdojo.BeltExam.repositiories.EventRepository;

@Service
public class EventService {
    private final EventRepository EventRepository;
	public EventService(EventRepository eventRepository) {
		this.EventRepository = eventRepository;
	}
	
	public List<Event> all(){
		return EventRepository.findAll();
	}
	
	public Event createEvent(Event event, User user) {
		event.setHoster(user);
		return EventRepository.save(event);
	}
	
	public Event findEvent(Long id) {
		Optional<Event> record = EventRepository.findById(id);
		if(record.isPresent()) {
			return record.get();
		} else {
			return null;
		}
	}
	
	public List<Event> sameState(String state){
		return EventRepository.findEventByState(state);
	}
	
	public List<Event> diffState(String state){
		return EventRepository.findEventByStateNot(state);
	}

	public void joinEvent(Event event, User user) {
		List<User> joiners = event.getAttendees();
		joiners.add(user);
		event.setAttendees(joiners);
		EventRepository.save(event);
	}

	public void leaveEvent(Event event, User user) {
		List<User> joiners = event.getAttendees();
		joiners.remove(user);
		event.setAttendees(joiners);
		EventRepository.save(event);
	}

	public void update(Event event) {
		EventRepository.save(event);
	}

	public void delete(Event event) {
		EventRepository.deleteById(event.getId());
	}
    
}
