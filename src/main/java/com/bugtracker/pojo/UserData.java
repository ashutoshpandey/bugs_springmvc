package com.bugtracker.pojo;

import java.util.List;

import com.bugtracker.entity.User;

public class UserData {

	private String message;

	private List<User> users;
	
	private boolean found;

	public String getMessage() {
		return message;
	}

	public List<User> getUsers() {
		return users;
	}

	public boolean isFound() {
		return found;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
}