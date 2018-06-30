package com.example.demo.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.demo.dominios.Restaurant;
import com.example.demo.dominios.User;
import com.example.demo.dominios.Utility;
import com.example.demo.services.DistrictServiceImplement;
import com.example.demo.services.RestaurantServiceImplement;
import com.example.demo.services.UserServiceImplement;

@Controller
public class RestaurantController {
	
	static final String MAIN_PAGE = "redirect:/dashboard/restaurant/all/";
	static final String IS_ERROR = "isError";
	static final String MESSAGE = "message";
	static final String USER_ID = "userId";

	@Autowired
	RestaurantServiceImplement restaurantService;

	@Autowired
	DistrictServiceImplement districtService;

	@Autowired
	UserServiceImplement userService;

	@RequestMapping(value = "/dashboard/", method = RequestMethod.GET)
	public String dashboard() {
		return "dashboard";
	}

	@RequestMapping(value = "/dashboard/restaurant/all/", method = RequestMethod.GET)
	public String restaurants(final Model model, final HttpSession session) {
		List<Restaurant> restaurants = restaurantService.findAll((int) session.getAttribute(USER_ID));
		model.addAttribute("restaurants", restaurants);
		return "restaurants";
	}

	@RequestMapping(value = "/dashboard/restaurant/add/", method = RequestMethod.GET)
	public String addRestaurant(Model model) {
		model.addAttribute("districts", districtService.findAll());
		model.addAttribute("objRestaurant", new Restaurant());
		return "addRestaurant";
	}

	@RequestMapping(value = "/dashboard/restaurant/edit/{id}", method = RequestMethod.GET)
	public String editRestaurant(Model model, @PathVariable int id) {
		Restaurant objRestaurant = restaurantService.findOne(id);
		model.addAttribute("objRestaurant", objRestaurant);
		model.addAttribute("districts", districtService.findAll());
		return "editRestaurant";

	}

	@RequestMapping(value = "/dashboard/restaurant/save/", method = RequestMethod.POST)
	public String save(@Valid Restaurant objRestaurant, BindingResult bindingResult,HttpSession session, RedirectAttributes redirectAttributes) {
		String message;
		boolean isError;
		try {
			if (bindingResult.hasErrors() || !restaurantService.validateHour(objRestaurant.getOpenHour(), objRestaurant.getCloseHour())) {
				message = "Flujo fallido.";
				isError = true;
				redirectAttributes.addFlashAttribute(MESSAGE, message);
				redirectAttributes.addFlashAttribute(IS_ERROR, isError);
				return MAIN_PAGE;
			} else {
				message = "Flujo exitoso.";
				isError = false;
				redirectAttributes.addFlashAttribute(MESSAGE, message);
				redirectAttributes.addFlashAttribute(IS_ERROR, isError);
				User user = userService.findOne((int) session.getAttribute(USER_ID));
				objRestaurant.setUser(user);
				restaurantService.save(objRestaurant);
				return MAIN_PAGE;
			}
		} catch (Exception ex) {
			return MAIN_PAGE;
		}
	}

	@RequestMapping(value = "/dashboard/restaurant/delete/{id}", method = RequestMethod.GET)
	public String deleteRestaurant(@PathVariable int id, Model model) {
		int restaurantId =id;
		model.addAttribute("restaurantId", restaurantId);
		model.addAttribute("objUtility", new Utility());
		return "deleteRestaurant";
	}
	@RequestMapping(value = "/dashboard/restaurant/delete/{id}", method = RequestMethod.POST)
	public String deleteRestaurantCompleted(@PathVariable int id, Model model, RedirectAttributes redirectAttributes,HttpSession session, Utility objUtility) {
		String message;
		boolean isError;
		boolean isValid = userService.validatePassword((int)session.getAttribute(USER_ID), objUtility.getPassword());
		try {
			if (isValid && objUtility.isConfirmation()) {
				restaurantService.delete(id);
				message = "Flujo exitoso.";
				isError = false;
				redirectAttributes.addFlashAttribute(MESSAGE, message);
				redirectAttributes.addFlashAttribute(IS_ERROR, isError);
				return MAIN_PAGE;
			} else {
				message = "Flujo fallido.";
				isError = true;
				redirectAttributes.addFlashAttribute(MESSAGE, message);
				redirectAttributes.addFlashAttribute(IS_ERROR, isError);
				return MAIN_PAGE;
			}
		} catch (Exception ex) {
			return MAIN_PAGE;
		}	}
	
}
