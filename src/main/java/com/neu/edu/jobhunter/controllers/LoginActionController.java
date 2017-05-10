package com.neu.edu.jobhunter.controllers;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.boot.spi.AdditionalJaxbMappingProducer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.neu.edu.jobhunter.dao.LoginActionDAO;
import com.neu.edu.jobhunter.dao.PersonDAO;
import com.neu.edu.jobhunter.pojo.Employer;
import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

@Controller
public class LoginActionController {
	/*
	 * Method is defined when any user logins to the system. It will check for
	 * the credentials from the User table and compare Based on the result it
	 * will navigate to the page of the role.
	 */

	@RequestMapping(value = "/login.htm", method = RequestMethod.POST)

	protected String loginUser(@ModelAttribute("user") User user, HttpServletRequest req, HttpServletResponse res,
			Model model) {
		try {
			System.out.print("Login Check \n");
			LoginActionDAO loginActionDAO = new LoginActionDAO();
			System.out.print("Trying to login to the System\n");

			User usr = loginActionDAO.checkLogin(user.getUserName(), user.getPassword());
			// System.out.println(user.getFirstName());
			System.out.println(user.getUserName());

			PrintWriter out;
			if (usr != null) {

				if (usr.getRole().equalsIgnoreCase("applicant")) {
					Person person = (Person) usr;

					model.addAttribute("person", person);
					return "userlogin";
				}
				else
				{
					Employer employer = (Employer) usr;

					model.addAttribute("employer", employer);
					return "employerLogin";
				}

			}

			else {

				model.addAttribute("errormsg", "*Invalid credentials*");
			}
			// DAO.close();
		} catch (Exception e) {
			System.out.println("Exception: " + e.getMessage());
		}

		return "index";
	}

}
