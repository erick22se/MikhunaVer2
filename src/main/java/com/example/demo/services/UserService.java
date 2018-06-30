package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dominios.User;

@Service
public interface UserService {

	public List<User> findAll();
	
	public User findOne(String email, String password);
	
	public User findOne(int userId);
	
	public User save(User user);
	
	public void delete(int userId);
	
	public boolean validatePassword(int userId, String password);

}
