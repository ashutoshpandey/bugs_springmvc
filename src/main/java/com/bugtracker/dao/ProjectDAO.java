package com.bugtracker.dao;

import com.bugtracker.entity.Project;

public interface ProjectDAO {

	boolean saveProject(Project project);

	boolean updateProject(Project project);

	boolean getProjects(String status);

	Project findProject(int projectId);
}
