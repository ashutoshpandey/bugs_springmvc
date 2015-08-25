package com.bugtracker.dao;

import javax.annotation.PostConstruct;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class HibernateUtil extends HibernateDaoSupport{

	@Autowired
	private SessionFactory sessionFactory;

	@PostConstruct
	public void init() {
		setSessionFactory(sessionFactory);
	}	
	
	public Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}
}
