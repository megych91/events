package com.codingdojo.BeltExam.repositiories;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

import com.codingdojo.BeltExam.models.Message;

public interface MessageRepository extends CrudRepository<Message, Long>{

    List<Message> findAll();

}
