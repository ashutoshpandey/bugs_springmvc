package com.bugtracker.service;

import java.util.List;

import com.bugtracker.entity.Project;

public interface ProjectService {

	boolean saveProject(Project project);

	boolean updateProject(Project project);

	List<Project> getProjects(String status);
	
	Project findProject(int projectId);

	int getProjectCount(String type);
}
