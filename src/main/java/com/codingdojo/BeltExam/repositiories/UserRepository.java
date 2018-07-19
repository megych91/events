package com.codingdojo.BeltExam.repositiories;

import org.springframework.data.repository.CrudRepository;

import com.codingdojo.BeltExam.models.User;

public interface UserRepository extends CrudRepository<User, Long>{
    User findByEmail(String email);
}
