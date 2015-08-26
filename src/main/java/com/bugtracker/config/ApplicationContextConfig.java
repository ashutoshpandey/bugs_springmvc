package com.bugtracker.config;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.bugtracker.dao.BugDAO;
import com.bugtracker.dao.BugDAOImpl;
import com.bugtracker.dao.ProjectDAO;
import com.bugtracker.dao.ProjectDAOImpl;
import com.bugtracker.dao.UserDAO;
import com.bugtracker.dao.UserDAOImpl;

@Configuration
@ComponentScan("com")
@EnableTransactionManagement
public class ApplicationContextConfig {
 
	@Bean(name = "viewResolver")
	public InternalResourceViewResolver getViewResolver() {
	    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
	    viewResolver.setPrefix("/WEB-INF/views/");
	    viewResolver.setSuffix(".jsp");
	    return viewResolver;
	}
 
	@Bean(name = "dataSource")
	public DataSource getDataSource() {
	    BasicDataSource dataSource = new BasicDataSource();
	    dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	    dataSource.setUrl("jdbc:mysql://localhost:3306/springbugs");
	    dataSource.setUsername("root");
	    dataSource.setPassword("");
	 
	    return dataSource;
	}
	
	@Autowired
	@Bean(name = "sessionFactory")
	public SessionFactory getSessionFactory(DataSource dataSource) {
	 
	    LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
	 
	    sessionBuilder.scanPackages("com.bugtracker.entity");	    
	    sessionBuilder.setProperty("hibernate.show_sql", "true");
	    sessionBuilder.addProperties(getHibernateProperties());
	 
	    return sessionBuilder.buildSessionFactory();
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.hbm2ddl.auto", "create");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
	    return properties;
	}
	
	@Autowired
	@Bean(name = "transactionManager")
	public HibernateTransactionManager getTransactionManager(
	        SessionFactory sessionFactory) {
	    HibernateTransactionManager transactionManager = new HibernateTransactionManager(
	            sessionFactory);
	 
	    return transactionManager;
	}
    
    @Autowired
    @Bean(name = "userDAO")
    public UserDAO getUserDao(SessionFactory sessionFactory) {
    	return new UserDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "projectDAO")
    public ProjectDAO getProjectDao(SessionFactory sessionFactory) {
    	return new ProjectDAOImpl(sessionFactory);
    }
    
    @Autowired
    @Bean(name = "bugDAO")
    public BugDAO getBugDao(SessionFactory sessionFactory) {
    	return new BugDAOImpl(sessionFactory);
    }
}