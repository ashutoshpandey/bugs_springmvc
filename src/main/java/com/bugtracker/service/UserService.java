package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.User;

public interface UserService {

	List<User> getAllUsers();

	User findUser(int userId);
	
	boolean saveUser(User user);
	
	boolean updateUser(User user);

	User isValidUser(String email, String password);
}
