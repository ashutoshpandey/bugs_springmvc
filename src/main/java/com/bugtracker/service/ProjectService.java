package com.bugtracker.service;

import org.springframework.stereotype.Service;

import com.bugtracker.entity.Project;

@Service
public interface ProjectService {

	boolean saveProject(Project project);

	boolean updateProject(Project project);

	boolean getProjects(String status);
	
	Project findProject(int projectId);
}
