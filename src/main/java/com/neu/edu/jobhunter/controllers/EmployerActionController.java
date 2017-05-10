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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neu.edu.jobhunter.dao.EmployerDAO;
import com.neu.edu.jobhunter.dao.PersonDAO;
import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Job;
import com.neu.edu.jobhunter.pojo.Person;

@Controller

public class EmployerActionController {

	@RequestMapping(value = "/addjob.htm", method = RequestMethod.POST)
	
	public String addJob(@ModelAttribute("job") Job job, HttpServletRequest req, HttpServletResponse res, Model model) {
		System.out.println("Getting into adding a job");
		HttpSession userSession = req.getSession();
		Employer employer = (Employer) userSession.getAttribute("employer");
		EmployerDAO employerDAO = new EmployerDAO();
		System.out.println("\nEntering job information");
		job = employerDAO.addJob(job, employer);
		if (job != null) {

		}
		return "employerLogin";
	}

	@RequestMapping(value = "/updatejob.htm", method = RequestMethod.GET)

	public String updateJob(@ModelAttribute("job") Job job, HttpServletRequest req, HttpServletResponse res,
			Model model) {
		System.out.println("Retrieving the job json");
		PrintWriter out;
		HttpSession userSession = req.getSession();
		Employer employer = (Employer) userSession.getAttribute("employer");
		long user_id = employer.getUserID();
		System.out.println(user_id);
		List<Job> jobList = new ArrayList<Job>();
		JSONObject jsonResult = new JSONObject();
		// String jobName = (String) req.getParameter("jobName");
		// String jobCategory = (String) req.getParameter("jobCategory");
		try {
			JSONArray Array = new JSONArray();
			EmployerDAO employerDAO = new EmployerDAO();
			jobList = employerDAO.updateJobs(user_id);
			// jobList = personDAO.getJobEmployer();
			if (jobList.isEmpty()) {
				// model.addAttribute("errorMsg", );
				out = res.getWriter();
				out.write("Oops! Please enter an option to choose");

			}

			else {
				for (Job retrieveJob : jobList) {

					JSONObject item = new JSONObject();
					item.put("jobID", retrieveJob.getJobID());
					item.put("jobName", retrieveJob.getJobName());
					item.put("jobCategory", retrieveJob.getJobCategory());
					item.put("employerName", retrieveJob.getEmployer().getEmployerName());
					Array.put(item);
				}
				jsonResult.put("results", Array);
				String message = jsonResult.toString();
				System.out.println(message);
				model.addAttribute(Array);

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

	@RequestMapping(value = "/deletejob.htm", method = RequestMethod.POST)
	public String deleteJob(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {

		System.out.println("Method to delete the job!");
		// Job j = (Job)req.getParameter("jsonGlobal");
		EmployerDAO employerDAO = new EmployerDAO();
		String jobid_string = req.getParameter("jobId");
		long jobid = Long.parseLong(jobid_string);
		HttpSession userSession = req.getSession();
		Employer employer = (Employer) userSession.getAttribute("employer");
		String message = employerDAO.deleteJob(jobid);
		if (message != "") {
			PrintWriter out;
			out=res.getWriter();
			out.println("The Job Has been Deleted.");

		}

		// long job_id = jsonJob.getJobID();
		return null;

	}

	@RequestMapping(value = "/updatejob.htm", method = RequestMethod.POST)
	public String updateJob(HttpServletRequest req, HttpServletResponse res, Model model) throws Exception {
		System.out.println("Method to update the job!");
		// Job j = (Job)req.getParameter("jsonGlobal");
		EmployerDAO employerDAO = new EmployerDAO();
		String jobid_string = req.getParameter("jobId");

		long jobid = Long.parseLong(jobid_string);
		System.out.println("jobid");
		req.getParameter("jobName");
		HttpSession userSession = req.getSession();
		Employer employer = (Employer) userSession.getAttribute("employer");
		String message = employerDAO.deleteJob(jobid);
		if (message != "") {

		}

		// long job_id = jsonJob.getJobID();
		return "employerLogin";
	}
}
