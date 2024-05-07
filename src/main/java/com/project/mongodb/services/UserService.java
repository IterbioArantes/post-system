package com.project.mongodb.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.mongodb.domain.User;
import com.project.mongodb.repositories.UserRepository;
import com.project.mongodb.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> findAll(){
		return userRepository.findAll();
	}
	
	public User findById(String id) {
		
		Optional<User> user = userRepository.findById(id);
		return user.orElseThrow(() -> new ObjectNotFoundException(id));
	}
	
	public User insert(User user) {
		return userRepository.save(user);
	}
	
	public void delete(String id) {
		findById(id);
		userRepository.deleteById(id);
	}
	
	public User update(String id, User user) {

		User userToUpdate = findById(id);
		
		userToUpdate.setName(user.getName());
		userToUpdate.setEmail(user.getEmail());
		
		return userRepository.save(userToUpdate);
	}
}