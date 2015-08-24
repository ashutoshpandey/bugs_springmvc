package com.bugtracker.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.bugtracker.dao.ProjectDAO;
import com.bugtracker.entity.Project;

public class ProjectServiceImpl implements ProjectService{

	@Autowired
	private ProjectDAO dao;
	
	@Override
	public boolean saveProject(Project project) {

		return dao.saveProject(project);
	}

	@Override
	public boolean updateProject(Project project) {
		
		return dao.updateProject(project);
	}

	@Override
	public boolean getProjects(String status) {
		
		return dao.getProjects(status);
	}

	@Override
	public Project findProject(int projectId) {
		
		return dao.findProject(projectId);
	}
}
