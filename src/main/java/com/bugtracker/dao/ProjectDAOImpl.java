package com.bugtracker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bugtracker.entity.Project;

@Transactional
@Repository
public class ProjectDAOImpl extends HibernateUtil implements ProjectDAO {

	@Override
	public boolean saveProject(Project project) {
		
		Session session = getCurrentSession();
		
		session.save(project);
		
		return true;
	}

	@Override
	public boolean updateProject(Project project) {
		
		Session session = getCurrentSession();
		
		session.update(project);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Project> getProjects(String status) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Project as pr where pr.status='active'");
		
		return (List<Project>)query.list();
	}

	@Override
	public Project findProject(int projectId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Project as pr where pr.projectID=:projectID");
		query.setInteger("projectID", projectId);
		
		return query.list().isEmpty() ? null : (Project)query.list().get(0);
	}

}
