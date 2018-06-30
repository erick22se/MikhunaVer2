package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominios.User;
import com.example.demo.repositories.UserRepository;

@Service
public class UserServiceImplement implements UserService {

	@Autowired
	public UserRepository repository;
	
	@Override
	public List<User> findAll() {		
		return repository.findAll();
	}

	@Override
	public User findOne(String email, String password) {
		
		List<User>users = findAll();
		for (User user : users) {
			if(user.getEmail().equalsIgnoreCase(email) && user.getPassword().equalsIgnoreCase(password)) {
				return user;
			}			
		}
		return null;	
	}

	@Override
	public User findOne(int userId) {
		
		return repository.findById(userId).orElse(null);
	}

	@Override
	public boolean validatePassword(int userId, String password) {
		User user = repository.findById(userId).orElse(null);
		if(user.getPassword().equalsIgnoreCase(password))
			return true;
		else
			return false;
	}

	@Override
	public User save(User user) {
		return repository.save(user);
	}

	@Override
	public void delete(int userId) {
		repository.deleteById(userId);
	}

}
