package com.codingdojo.BeltExam.repositiories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.codingdojo.BeltExam.models.Event;

public interface EventRepository extends CrudRepository<Event, Long>{

    List<Event> findAll();

    List<Event> findEventByState(String state);

    List<Event> findEventByStateNot(String state);

}
