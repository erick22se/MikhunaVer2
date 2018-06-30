package com.example.demo.test.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dominios.User;
import com.example.demo.services.UserService;

public class UserServiceImplementTest implements UserService {

	private static List<User> users;
	
	public UserServiceImplementTest() {
		super();
		users = new ArrayList<>();
	}

	@Override
	public List<User> findAll() {		
		return users;
	}

	@Override
	public User findOne(String email, String password) {
		for (User user : users) {
			if(user.getEmail().equals(email) && user.getPassword().equals(password))
				return user;
		}
		return null;
	}

	@Override
	public User findOne(int userId) {
		for (User user : users) {
			if(user.getUserId() == userId)
				return user;
		}
		return null;
	}

	@Override
	public User save(User user) {
		
		if(user.getUserId() == 0) {
			user.setUserId(users.size() + 1);
			users.add(user);
			return user;
		}else {
			users.set(user.getUserId() - 1, user);
			return user;
		}		
	}

	@Override
	public void delete(int userId) {
		users.remove(userId - 1);
	}

	@Override
	public boolean validatePassword(int userId, String password) {
		for (User user : users) {
			if(user.getUserId() == userId && user.getPassword().equals(password))
				return true;
		}
		return false;
	}

}
