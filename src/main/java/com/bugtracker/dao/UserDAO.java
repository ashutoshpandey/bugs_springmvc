package com.bugtracker.dao;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bugtracker.entity.User;

@Service
public interface UserDAO {

	List<User> getAllUsers();

	User findUser(int userId);
	
	boolean saveUser(User user);
	
	boolean updateUser(User user);

	User isValidUser(String email, String password);
}
