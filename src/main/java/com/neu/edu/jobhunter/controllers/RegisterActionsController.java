package com.neu.edu.jobhunter.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.jobhunter.dao.PersonDAO;
import com.neu.edu.jobhunter.dao.EmployerDAO;
import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

@Controller

public class RegisterActionsController {

	@Autowired
	@Qualifier("personValidator")
	PersonValidator personValidator;

	@InitBinder("person")
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(personValidator);
	}

	@RequestMapping(value = "/createapplicant.htm", method = RequestMethod.POST)

	/*
	 * Method defined to create a user for candidate/employee. This is the POST
	 * method that will be called after a user Registers. This will create a new
	 * database entry for the Person and the User.
	 */
	protected String createApplicantPost(@ModelAttribute("person") Person person, BindingResult result, Model model)
			throws Exception {
		String viewname = "";
		personValidator.validate(person, result);
		if (result.hasErrors()) {
			return "registerApplicant";
		}

		try {
			// System.out.print("test");
			PersonDAO personDao = new PersonDAO();
			System.out.print("test1");

			viewname = personDao.create(person.getUserName(), person.getPassword(), person.getFirstName(),
					person.getLastName(), person.getEmail());
			if (viewname.equalsIgnoreCase("registerApplicant"))
				model.addAttribute("errormsg", "*Error : User for this Email id already present*");
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());

		}
		// return "index";
		return viewname;

	}

	/*
	 * This is the method which will be called when the user selects 'Sign In'
	 * option from the homescreen. This will redirect the user to the sign in
	 * page for the APPLICANT.
	 */
	@RequestMapping(value = "/createapplicant.htm", method = RequestMethod.GET)
	public String createApplicantGet(@ModelAttribute("user") User user) {

		// System.out.println("Here");
		return "registerApplicant";
	}

	@RequestMapping(value = "/loginuser.htm", method = RequestMethod.GET)
	public String loginUser(@ModelAttribute("user") User user) {

		System.out.println("Ready");
		return "index";
	}

	/*
	 * Method to goto the registration page for the employer. This will redirect
	 * to the jsp for the registration of the employer
	 */
	@RequestMapping(value = "/createemployer.htm", method = RequestMethod.GET)
	protected String createEmployerGet(@ModelAttribute("employer") Employer employer) {
		return "registerEmployer";
	}

	@RequestMapping(value = "/createemployer.htm", method = RequestMethod.POST)

	/*
	 * Method defined to create a user for candidate/employee. This is the POST
	 * method that will be called after a user Registers. This will create a new
	 * database entry for the Person and the User.
	 */
	protected String createEmployerPost(@ModelAttribute("employer") Employer employer) throws Exception {

		try {
			System.out.print("employerTest");
			EmployerDAO userDao = new EmployerDAO();
			System.out.print("test1");

			userDao.create(employer.getEmployerName(), employer.getEmployerCategory(), employer.getUserName(),
					employer.getPassword());

			// DAO.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "employerLogin";
	}
}
