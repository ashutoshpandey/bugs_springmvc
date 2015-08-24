package com.bugtracker.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bugtracker.entity.Project;

@Service
public interface ProjectService {

	boolean saveProject(Project project);

	boolean updateProject(Project project);

	boolean changeProjectStatus(Project project);

	boolean listProjects(int projectId);

	boolean projectDetail(int projectId);
	
	Project findProject(int projectId);

    /****************** json methods ***********************/

    List<Project> dataListProjects();
}
