package com.neu.edu.jobhunter.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogOutController {
	@RequestMapping(value = "/logout.htm", method = RequestMethod.GET)
	   public String logout(HttpServletRequest request) {
	       HttpSession session = request.getSession();
	       session.invalidate();
	       return "index";
	   }

}
