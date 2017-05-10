package com.neu.edu.jobhunter.controllers;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.neu.edu.jobhunter.pojo.Person;
import com.neu.edu.jobhunter.pojo.User;

public class PersonValidator implements Validator {

	@Override
	public boolean supports(Class<?> aClass) {
//		return aClass.equals(Person.class);
		 return Person.class.equals(aClass);
	}

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		Person person = (Person) object;
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName",
		// "error.invalid.user", "First Name Required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName",
		// "error.invalid.user", "Last Name Required");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "userName",
		// "error.invalid.user", "Username already exists in the system");
		// ValidationUtils.rejectIfEmptyOrWhitespace(errors, "password",
		// "error.invalid.password", "Password Required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "error.invalid.email.emailId",
				"Please enter an email");
		

	}

}
