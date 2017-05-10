package com.neu.edu.jobhunter.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.criterion.Restrictions;
import org.springframework.ui.Model;

import com.neu.edu.jobhunter.pojo.Education;
import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Experience;
import com.neu.edu.jobhunter.pojo.Job;
import com.neu.edu.jobhunter.pojo.JobPersonBridge;
import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

/*
 * This DAO is for connecting to the Person. This is mainly for creation 
 * of the person after the first registration page.
 */
public class PersonDAO extends DAO {

	public PersonDAO() {
	}

	/*
	 * public Person login(String userName, String password) throws Exception {
	 * try { begin(); Query q = getSession().createQuery(
	 * "from User where userName = :userName and password = :password");
	 * q.setParameter("userName", userName); q.setParameter("password",
	 * password); Person person = (Person) q.uniqueResult(); commit(); return
	 * person; } catch (HibernateException e) { rollback(); throw new Exception(
	 * "Could not get user " + userName, e); } }
	 */

	public String create(String userName, String password, String firstName, String lastName, String email)
			throws Exception {
		String viewname = "";
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
			close();
			viewname = "userlogin";
		} catch (HibernateException e) {
			rollback();

			viewname = "registerApplicant";
			// throw new AdException("Could not create user " + userName, e);
			// Model model;
			//
			// throw new Exception("Exception while creating user: " +
			// e.getMessage());

		}
		return viewname;

	}

	public Education addEducation(Education education, Person person) {

		begin();
		System.out.println("Trying to update Education");
		// education.setApplicant(person);
		education.setPerson(person);

		person.getEducationList().add(education);

		getSession().save(education);
		commit();
//		close();
		// getSession().save(person);
		return education;

	}

	public Experience addExperience(Experience experience, Person person) {

		begin();
		System.out.println("Trying to update Experience");
		experience.setPerson(person);

		person.getExperienceList().add(experience);

		getSession().save(experience);
		commit();
		close();
		return experience;
	}

	public Education checkEducation(long userID) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Education where user_id = :userID");
			q.setParameter("user_id", userID);
			// q.setParameter("password", password);
			Education edu = (Education) q.uniqueResult();
			commit();
			close();
			return edu;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get education ");
		}
	}

	public Job searchJob(String jobName, String jobCategory) throws Exception {
		try {
			begin();
			Query q = getSession().createQuery("from Job where jobName = :jobName or jobCategory = :jobCategory");
			q.setParameter("jobName", jobName);
			q.setParameter("jobCategory", jobCategory);
			Job job = (Job) q.uniqueResult();
			// commit();
			close();
			return job;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get job ");
		}
	}

	public List<Job> getJobs(String jobName, String jobCategory) throws Exception {
		try {
			begin();
			System.out.println("creating the list");
			Query q = getSession().createQuery("from Job where jobName = :jobName or jobCategory = :jobCategory");
			q.setParameter("jobName", jobName);
			q.setParameter("jobCategory", jobCategory);
			List<Job> jobList = q.list();

			// commit();
			close();
			return jobList;
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get jobs ");

		}
	}

	/*
	 * public List<Job> getJobEmployer() throws Exception { try {
	 * 
	 * Criteria crit = getSession().createCriteria(Employer.class); Criteria
	 * jobCriteria = crit.createCriteria("job");
	 * jobCriteria.add(Restrictions.eq("userID",) List<Job> results =
	 * crit.list(); return results;
	 * 
	 * 
	 * begin(); System.out.println("creating the list"); Query q =
	 * getSession().createQuery(
	 * "from Employer e, Job j where e.userID=j.User_Id "); //
	 * q.setParameter("jobName", jobName); // q.setParameter("jobCategory",
	 * jobCategory); List<Job> jobList = q.list();
	 * 
	 * // commit(); return jobList;
	 * 
	 * } catch (HibernateException e) { rollback(); throw new Exception(
	 * "Could not get jobs ");
	 * 
	 * }
	 * 
	 * }
	 */
	public String applyJob(long jobid, Person person) throws Exception {

		try {
			begin();
			System.out.println("Trying to apply to the job");
			begin();

			Query q = getSession().createQuery("from Job where jobID = :jobid");

			Job job = (Job) q.uniqueResult();

			JobPersonBridge jobBridge = new JobPersonBridge();

			// jobBridge.setJobID(jobid);
			jobBridge.setJob(job);
			jobBridge.setPerson(person);

			commit();
			close();

			return "employerLogin";
		} catch (HibernateException e) {
			rollback();
			throw new Exception("could not delete job ");

		}
	}

	public String updateProfile(String firstName, String lastName, String email, long id) throws Exception {
		try {
			begin();
			System.out.println("inside update profile DAO");
			Query q = getSession().createQuery("from Person where userID = :id");
			// q.setParameter("firstName", firstName);
			// Query q2 = getSession().createQuery("from Person where lastName =
			// :lastName ");
			// q2.setParameter("lastName", lastName);
			// Query q3 = getSession().createQuery("from Person where email =
			// :email ");
			q.setParameter("id", id);
			Person p = (Person) q.uniqueResult();

			if (firstName != "") {
				p.setFirstName(firstName);
			}
			if (lastName != null) {
				p.setLastName(lastName);
			}
			if (email != null) {
				p.setEmail(email);
			}
			getSession().update(p);
			commit();
			
			return "success";
		} catch (HibernateException e) {
			rollback();
			throw new Exception("Could not get job ");
		}
	}
	public boolean updatePassword(String password,long id)
	{
		begin();
		System.out.println("inside update profile DAO");
		Query q = getSession().createQuery("from User where userID = :id");
		q.setParameter("id", id);
		User u = (User)q.uniqueResult();
		if(password!=null)
		{
			u.setPassword(password);
		}
		getSession().update(u);
		commit();
		getSession().close();
		return true;
		
	}
}
