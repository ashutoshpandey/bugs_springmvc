package com.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bugtracker.dao.UserDAO;
import com.bugtracker.entity.User;

public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO dao;
	
	@Override
	public List<User> getAllUsers() {
		
		return dao.getAllUsers();
	}

	@Override
	public User findUser(int userId) {
		
		return dao.findUser(userId);
	}

	@Override
	public boolean saveUser(User user) {
		
		return dao.saveUser(user);
	}

	@Override
	public boolean updateUser(User user) {
		
		return dao.updateUser(user);
	}
}