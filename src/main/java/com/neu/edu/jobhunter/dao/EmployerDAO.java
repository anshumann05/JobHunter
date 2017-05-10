package com.neu.edu.jobhunter.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;

import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Job;
import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

/*
 * This DAO class is specially for Employer actions. The employer login page will 
 * be connected to the database through this DAO action.
 */
public class EmployerDAO extends DAO {

	public EmployerDAO() {
	}

	public Job addJob(Job job, Employer employer) {
		begin();
		System.out.println("Trying to add a new Job");
		job.setEmployer(employer);
		employer.getJobList().add(job);
		getSession().save(job);
		commit();
		
		return job;
	}

	public String deleteJob(long jobid) throws Exception {

		try {
			begin();
			System.out.println("Trying to delete the job");
			begin();
			Query q = getSession().createQuery("from Job where jobID = :jobid");
			q.setParameter("jobid", jobid);
			Job job = (Job) q.uniqueResult();
			Employer emp = job.getEmployer();
			emp.getJobList().remove(job);
			getSession().save(emp);
			

			getSession().delete(job);
			
			commit();
			
			return "employerLogin";
		} catch (HibernateException e) {
			rollback();
			throw new Exception("could not delete job ");

		}
	}

	public Employer create(String employerName, String employerCategory, String userName, String password)
			throws Exception {
		try {
			begin();
			System.out.println("inside DAO");

			Employer employer = new Employer(employerName, employerCategory);
			User user = new User();

			// person.setEmail(email);
			// person.setFirstName(firstName);
			// person.setLastName(lastName);
			// employer.setEmployerName(employerName);
			// employer.setEmployerCategory(employerCategory);
			employer.setUserName(userName);
			employer.setPassword(password);
			employer.setRole("employer");
			user.setRole("employer");

			user.setUserName(userName);
			user.setPassword(password);

			// user.setEmail(emailId);

			getSession().save(employer);

			commit();
			
			return employer;
		} catch (HibernateException e) {
			rollback();
			// throw new AdException("Could not create user " + username, e);
			throw new Exception("Exception while creating user: " + e.getMessage());
		}

	}

	public List<Job> updateJobs(long user_id) throws Exception {
		try {
			begin();
			System.out.println("creating the list");
			Query q = getSession().createQuery("from Job where User_Id = :user_id");
			 q.setParameter("user_id", user_id);
			// q.setParameter("jobCategory", jobCategory);
			List<Job> jobList = q.list();

			// commit();
			
			return jobList;
			
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get jobs ");

		}
	}
}
