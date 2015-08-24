package com.bugtracker.pojo;

import java.util.List;

import com.bugtracker.entity.Bug;

public class BugData {

	private String message;

	private List<Bug> bugs;
	
	private boolean found;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<Bug> getBugs() {
		return bugs;
	}

	public void setBugs(List<Bug> bugs) {
		this.bugs = bugs;
	}

	public boolean isFound() {
		return found;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
}
