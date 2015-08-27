package com.bugtracker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugFile;
import com.bugtracker.entity.BugUser;

@Transactional
@Repository
public class BugDAOImpl extends HibernateUtil implements BugDAO {
	
	@Override
	public boolean saveBug(Bug bug) {
		
		Session session = getCurrentSession();
		
		session.save(bug);
		
		return true;
	}

	@Override
	public boolean updateBug(Bug bug) {
		
		Session session = getCurrentSession();
		
		session.update(bug);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Bug> getBugs(int projectId, String bugType) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Bug as bug where bug.projectID=:projectID and bug.bugType=:bugType");
		query.setString("bugType" , bugType);
		query.setInteger("projectID", projectId);
		
		return (List<Bug>)query.list();
	}

	@Override
	public boolean saveBugComment(BugComment bugComment) {
		
		Session session = getCurrentSession();
		
		session.save(bugComment);
		
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BugComment> getBugComments(int bugId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from BugComment as bc where bc.bugID=:bugID and bc.status='active'");
		query.setInteger("bugID", bugId);
		
		return (List<BugComment>)query.list();
	}

	@Override
	public Bug findBug(int bugId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from BugComment as bc where bc.bugID=:bugID");
		query.setInteger("bugID", bugId);
		
		return query.list().isEmpty() ? null : (Bug)query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BugFile> getBugFiles(Integer bugId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from BugFile as bc where bc.bugID=:bugID and bc.status='active'");
		query.setInteger("bugID", bugId);
		
		return (List<BugFile>)query.list();
	}

	@Override
	public int getBugCount(String type) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from Bug as bug where bug.status=:status");
		query.setString("status", type);
		
		return query.list().size();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BugUser> getUserBugs(Integer userId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from BugUser as bu where bu.userId=:userId");
		query.setInteger("userId", userId);
		
		return (List<BugUser>)query.list();
	}

}
