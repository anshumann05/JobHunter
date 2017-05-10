package com.neu.edu.jobhunter.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import com.neu.edu.jobhunter.pojo.Person;

public class RegistrationValidationDAO extends DAO {
	public RegistrationValidationDAO() {

	}

	public Person checkEmail(String email) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Person where email = :email");
			q.setParameter("email", email);
			Person person = (Person) q.uniqueResult();
			commit();
			getSession().close();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get email " + email, e);
		}

	}

	public Person checkUserName(String userName) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Person where userName = :userName");
			q.setParameter("userName", userName);
			Person person = (Person) q.uniqueResult();
			commit();
			getSession().close();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e);
		}

	}

}
