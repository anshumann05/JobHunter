package com.neu.edu.jobhunter.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

public class LoginActionDAO extends DAO{
	
	public LoginActionDAO(){
		System.out.println("Inside LoginActionDAO");
		
	}
	
	public User checkLogin(String userName, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setParameter("userName", userName);
			q.setParameter("password", password);
			User user = (User) q.uniqueResult();
			commit();
			
			return user;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e);
		}
	}

}
