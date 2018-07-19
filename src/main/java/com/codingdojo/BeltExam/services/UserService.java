package com.codingdojo.BeltExam.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import com.codingdojo.BeltExam.models.User;
import com.codingdojo.BeltExam.repositiories.UserRepository;


@Service
public class UserService{
	@Autowired
	private UserRepository userRepository;

	public UserService(UserRepository userRepository){
		this.userRepository = userRepository;
	}

	public User register(User user){
		User emailExists = (User) findByEmail(user.getEmail());

		if(emailExists != null){
			return null;
		}else{
			user.setPassword(BCrypt.hashpw(user.getPassword(),BCrypt.gensalt()));
			return userRepository.save(user);
		}
	}

	public User login(String email,String password){
		User emailExists = (User) findByEmail(email);
		
		if(emailExists != null) {
			if(BCrypt.checkpw(password, emailExists.getPassword() )){
				return emailExists;
			}else {
				return null;
			}
		}else {
			return null;
		}
	}

	public User findByEmail(String email){
		return userRepository.findByEmail(email);
	}
	
	public User findById(Long id) {
        Optional<User> u = userRepository.findById(id);
        if (u.isPresent()) return u.get();
        else return null;
    }




}
