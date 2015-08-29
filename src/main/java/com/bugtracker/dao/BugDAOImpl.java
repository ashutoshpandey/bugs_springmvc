package com.bugtracker.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bugtracker.entity.Bug;
import com.bugtracker.entity.BugComment;
import com.bugtracker.entity.BugCommentFile;
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
	public List<Bug> getBugs(int projectId, String status) {

		Session session = getCurrentSession();
		System.out.println(projectId + " , " + status);
		Query query = session.createQuery("from Bug as bug where bug.project.id=:projectId and bug.status=:status");
		query.setString("status", status);
		query.setInteger("projectId", projectId);

		return (List<Bug>) query.list();
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

		Query query = session.createQuery("from BugComment as bc where bc.bug.id=:bugId and bc.status='active'");
		query.setInteger("bugId", bugId);

		return (List<BugComment>) query.list();
	}

	@Override
	public Bug findBug(int bugId) {

		Session session = getCurrentSession();

		Query query = session.createQuery("from Bug as bc where bc.id=:bugId");
		query.setInteger("bugId", bugId);

		return query.list().isEmpty() ? null : (Bug) query.list().get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<BugFile> getBugFiles(Integer bugId) {

		Session session = getCurrentSession();

		Query query = session.createQuery("from BugFile as bc where bc.bug.id=:bugId and bc.status='active'");
		query.setInteger("bugId", bugId);

		return (List<BugFile>) query.list();
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
System.out.println("Hi");
		Session session = getCurrentSession();

		Query query = session.createQuery("from BugUser as bu where bu.user.id=:userId");
		query.setInteger("userId", userId);

		return (List<BugUser>) query.list();
	}

	@Override
	public boolean saveBugFile(BugFile bugFile) {

		Session session = getCurrentSession();

		session.save(bugFile);

		return true;
	}

	@Override
	public boolean saveBugUser(BugUser bugUser) {

		Session session = getCurrentSession();

		session.save(bugUser);

		return true;
	}

	@Override
	public boolean saveBugCommentFile(BugCommentFile bugCommentFile) {

		Session session = getCurrentSession();

		session.save(bugCommentFile);

		return true;
	}

	@Override
	public boolean changeBugUserStatus(int id, String status) {

		Session session = getCurrentSession();

		Criteria crit = session.createCriteria(BugUser.class);
		crit.add(Restrictions.eqProperty("bug.id", String.valueOf(id)));

		ScrollableResults items = crit.scroll();
		int count = 0;
		while (items.next()) {
			
			BugUser bugUser = (BugUser) items.get(0);
			bugUser.setStatus(status);
			
			session.saveOrUpdate(bugUser);
			
			if (++count % 100 == 0) {
				session.flush();
				session.clear();
			}
		}

		return true;
	}

}
