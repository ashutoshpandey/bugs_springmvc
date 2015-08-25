package com.bugtracker.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.bugtracker.entity.User;

@Transactional
@Repository
public class UserDAOImpl extends HibernateUtil implements UserDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from User as user where user.status='active'");
		
		return (List<User>)query.list();
	}

	@Override
	public User findUser(int userId) {
		
		Session session = getCurrentSession();
		
		Query query = session.createQuery("from User as user where user.userID=:userID");
		query.setInteger("userID", userId);
		
		return query.list().isEmpty() ? null : (User)query.list().get(0);
	}

	@Override
	public boolean saveUser(User user) {
		
		Session session = getCurrentSession();
		
		session.save(user);
		
		return true;
	}

	@Override
	public boolean updateUser(User user) {
		
		Session session = getCurrentSession();
		
		session.update(user);
		
		return true;
	}

}
