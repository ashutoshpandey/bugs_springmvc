package com.bugtracker.dao;

import java.util.List;

import com.bugtracker.entity.User;

public interface UserDAO {

	List<User> getAllUsers();

	User findUser(int userId);
	
	boolean saveUser(User user);
	
	boolean updateUser(User user);

	User isValidUser(String email, String password);
}
