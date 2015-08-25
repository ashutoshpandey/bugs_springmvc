package com.bugtracker.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bugtracker.dao.ProjectDAO;
import com.bugtracker.entity.Project;

@Service
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
	public List<Project> getProjects(String status) {
		
		return dao.getProjects(status);
	}

	@Override
	public Project findProject(int projectId) {
		
		return dao.findProject(projectId);
	}
}
