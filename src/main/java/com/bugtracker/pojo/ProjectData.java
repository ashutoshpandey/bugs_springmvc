package com.bugtracker.pojo;

import java.util.List;

import com.bugtracker.entity.Project;

public class ProjectData {

	private String message;

	private List<Project> projects;
	
	private boolean found;

	public String getMessage() {
		return message;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public boolean isFound() {
		return found;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public void setFound(boolean found) {
		this.found = found;
	}
}