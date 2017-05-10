package com.neu.edu.jobhunter.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.jobhunter.dao.PersonDAO;
import com.neu.edu.jobhunter.dao.LoginActionDAO;
import com.neu.edu.jobhunter.dao.RegistrationValidationDAO;
import com.neu.edu.jobhunter.pojo.Education;
import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

/**
 * Handles requests for the application home page.
 */
@Controller
public class AjaxValidationController {

	// private static final Logger logger =
	// LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws Exception
	 */
	/*
	 * ***************** METHOD DEFINITION ***************** This method is to
	 * check in the registration page of the applicant, if the user email exists
	 * or not.
	 * 
	 */
	@RequestMapping(value = "/checkEmailId.htm", method = RequestMethod.GET)
	public Person checkEmailId(@ModelAttribute("person") Person person, HttpServletRequest req, HttpServletResponse res)
			throws Exception {

		RegistrationValidationDAO registerDAO = new RegistrationValidationDAO();
		String emailId = (String) req.getParameter("emailid");
		System.out.println("Checking if the email is unique or not.");
		Person p1 = registerDAO.checkEmail(emailId);
		// Person p1 = applicantDao.checkEmail(person.getEmail());

		// System.out.println("I am in Ajax!!");

		PrintWriter out;
		try {
			if (p1 != null) {

				out = res.getWriter();
				out.println("Email id has already been registered.");

			}

		}

		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/*
	 * ***************** METHOD DEFINITION ***************** This method will
	 * check if the username is unique or not when registering and applicant.
	 */
	@RequestMapping(value = "/checkUsername.htm", method = RequestMethod.GET)
	public String checkUserName(@ModelAttribute("person") Person person, HttpServletRequest req,
			HttpServletResponse res) throws Exception {
		RegistrationValidationDAO registrationDAO = new RegistrationValidationDAO();
		String userName = (String) req.getParameter("userName");
		System.out.println("Checking if the username is unique or not.");
		Person prsn = registrationDAO.checkUserName(userName);
		// System.out.println("I am in Ajax!!");
		PrintWriter out;
		try {

			if (prsn != null) {
				out = res.getWriter();
				out.println("Username already exists in the system");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

}
