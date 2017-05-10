package com.neu.edu.jobhunter.dao;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.springframework.ui.Model;

import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

public class ApplicantDAO extends DAO {

	public ApplicantDAO() {
	}

	public Person login(String userName, String password) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from User where userName = :userName and password = :password");
			q.setParameter("userName", userName);
			q.setParameter("password", password);
			Person person = (Person) q.uniqueResult();
			commit();
			return person;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get user " + userName, e);
		}
	}

	public String create(String userName, String password, String firstName, String lastName, String email)
			throws Exception {
		String viewname="";
		try {
			begin();
			System.out.println("inside DAO");

			Person person = new Person(firstName, lastName, email);
			User user = new User();

			// person.setEmail(email);
			// person.setFirstName(firstName);
			// person.setLastName(lastName);
			person.setUserName(userName);
			person.setPassword(password);
			person.setRole("applicant");
			user.setUserName(userName);
			user.setPassword(password);
			user.setRole("applicant");

			// user.setEmail(emailId);

			getSession().save(person);

			commit();
			viewname= "userlogin";
		} catch (HibernateException e) {
			rollback();
			
			viewname = "registerApplicant";
			// throw new AdException("Could not create user " + userName, e);
//			Model model;
//			
//			throw new Exception("Exception while creating user: " + e.getMessage());
			
		}
		return viewname;

	}
	
	public Person checkEmail(String email) throws Exception
	{
		try{begin();
		Query q = getSession().createQuery("from Person where email = :email");
		q.setParameter("email",email);
		Person person =  (Person)q.uniqueResult();
		commit();
		return person;
	} catch (HibernateException e) {
		rollback();
		throw new Exception("Could not get email " + email, e);
	}
		
	}
	
	public Person checkUserName(String userName) throws Exception
	{
		try{begin();
		Query q = getSession().createQuery("from Person where userName = :userName");
		q.setParameter("userName",userName);
		Person person = (Person) q.uniqueResult();
		commit();
		return person;
	} catch (HibernateException e) {
		rollback();
		throw new Exception("Could not get user " + userName, e);
	}
		
	}
	

}
