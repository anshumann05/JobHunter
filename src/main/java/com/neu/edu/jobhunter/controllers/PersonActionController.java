package com.neu.edu.jobhunter.controllers;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.jobhunter.dao.EmployerDAO;
import com.neu.edu.jobhunter.dao.PersonDAO;
import com.neu.edu.jobhunter.pojo.Education;
import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Experience;
import com.neu.edu.jobhunter.pojo.Job;
import com.neu.edu.jobhunter.pojo.Person;

@Controller
public class PersonActionController {

	@RequestMapping(value = "/addeducation.htm", method = RequestMethod.POST)

	public String addEducation(@ModelAttribute("education") Education education, HttpServletRequest req,
			HttpServletResponse res, Model model) throws Exception {
		// String message = "";
		PrintWriter out;
		try {

			System.out.println("Education level   " + education.getDegreeLevel());
			HttpSession userSession = req.getSession();
			Person person = (Person) userSession.getAttribute("person");
			PersonDAO personDao = new PersonDAO();
			System.out.println("Entering education information");
			Education edu = personDao.addEducation(education, person);
			//
			if (edu != null) {
				out = res.getWriter();
				out.println("Education Details have been added");
				 model.addAttribute("msg", "Education Details have been added successfully");
			}
			// model.addAttribute("errormsg", "*Education Details have been
			// added successfully*");
			// }
		} catch (Exception e) {
			out = res.getWriter();
			out.println("Education Details could not be added");
			// TODO: handle exception
		}
		// return ;

		return "userlogin";

	}

	@RequestMapping(value = "/addexperience.htm", method = RequestMethod.POST)

	public String addEducation(@ModelAttribute("experience") Experience experience, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		try {
			// System.out.println("Education level" +
			// education.getDegreeLevel());
			HttpSession userSession = req.getSession();
			Person person = (Person) userSession.getAttribute("person");
			PersonDAO personDao = new PersonDAO();
			System.out.println("Entering experience information");
			personDao.addExperience(experience, person);

		} catch (Exception e) {
			// TODO: handle exception
		}

		return "userlogin";

	}

	@RequestMapping(value = "/searchjob.htm", method = RequestMethod.GET)

	public String searchJob(@ModelAttribute("job") Job job, HttpServletRequest req, HttpServletResponse res,
			Model model) throws Exception {
		PrintWriter out;
		List<Job> jobList = new ArrayList<Job>();
		JSONObject jsonResult = new JSONObject();
		String jobName = (String) req.getParameter("jobName");
		String jobCategory = (String) req.getParameter("jobCategory");
		try {
			JSONArray Array = new JSONArray();
			PersonDAO personDAO = new PersonDAO();
			jobList = personDAO.getJobs(jobName, jobCategory);
			// jobList = personDAO.getJobEmployer();
			if (jobList.isEmpty()) {
				 model.addAttribute("emptymessage","Oops! Please enter an option to choose");
				out = res.getWriter();
				out.write("Oops! Please enter an option to choose");

			}

			else {
				for (Job retrieveJob : jobList) {

					JSONObject item = new JSONObject();
					item.put("jobID", retrieveJob.getJobID());
					item.put("jobName", retrieveJob.getJobName());
					item.put("employerName", retrieveJob.getEmployer().getEmployerName());
					Array.put(item);
				}
				jsonResult.put("results", Array);
				String message = jsonResult.toString();
				System.out.println(message);

				if (message != "") {
					out = res.getWriter();
					out.write(message);
				}
			}

			// System.out.println("Education level" +
			// education.getDegreeLevel());

		} catch (Exception e) {
			// TODO: handle exception
		}

		return null;

	}

	@RequestMapping(value = "/applyjob.htm", method = RequestMethod.POST)
	public String applyJob(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		System.out.println("Method to apply to the job!");
		// Job j = (Job)req.getParameter("jsonGlobal");
		PersonDAO personDAO = new PersonDAO();
		String jobid_string = req.getParameter("jobId");
		long jobid = Long.parseLong(jobid_string);
		System.out.println(jobid_string);

		Person person = (Person) req.getAttribute("personId");
		System.out.println(person);
		HttpSession userSession = req.getSession();
		String message = personDAO.applyJob(jobid, person);
		if (message != "") {

		}

		// long job_id = jsonJob.getJobID();
		return null;

	}

	@RequestMapping(value = "/updateProfile.htm", method = RequestMethod.POST)
	public String updateJob(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		PrintWriter out;
		boolean success =false;
		HttpSession userSession = req.getSession();
		System.out.println("Method to update the user profile!");
		// Job j = (Job)req.getParameter("jsonGlobal");
		PersonDAO personDAO = new PersonDAO();
		String firstName = req.getParameter("firstName");
		String lastName = req.getParameter("lastName");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		Person person = (Person) userSession.getAttribute("person");
		long id = person.getUserID();
		success = checkPassword(password,id);
		
		System.out.println(id);

		String message = personDAO.updateProfile(firstName, lastName, email, id);

		if (message != "") {
			out = res.getWriter();
			out.println("Updated successfully");
			
		}
		if(success)
		{
			out = res.getWriter();
			out.println("Password Changed Successfully");
			
		}

		// long job_id = jsonJob.getJobID();
		return "userlogin";
		

	}
	
	public boolean checkPassword(String password,long id)
	{
		boolean result = false;
		PersonDAO personDAO = new PersonDAO();
		result = personDAO.updatePassword(password,id);
		return result;
	}

}
