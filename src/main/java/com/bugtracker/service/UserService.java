package com.bugtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bugtracker.entity.User;

@Service
public interface UserService {

	List<User> getAllUsers();

}
