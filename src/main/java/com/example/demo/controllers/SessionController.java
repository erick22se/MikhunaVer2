package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dominios.User;
import com.example.demo.services.UserServiceImplement;

@Controller
public class SessionController {

	static final String MAIN_PAGE = "redirect:/login/";
	
	@Autowired
	UserServiceImplement service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index() {
		return MAIN_PAGE;
	}

	@RequestMapping(value = "/login/", method = RequestMethod.GET)
	public String login(Model model) {
		model.addAttribute("objUser", new User());
		return "login";
	}

	@RequestMapping(value="/login/",method=RequestMethod.POST)
	public String login(@ModelAttribute User objUser, HttpSession session){	
		try {
			User user = service.findOne(objUser.getEmail(), objUser.getPassword());
			if(user == null) {
				return MAIN_PAGE;			
			}
			else {
				session.setAttribute("userId", user.getUserId());
				return "redirect:/dashboard/";
			}
		}catch(Exception ex) {
			return MAIN_PAGE;
		}
	}

	@RequestMapping(value = "/logout/", method = RequestMethod.GET)
	public String logout() {
		return MAIN_PAGE;
	}
}
