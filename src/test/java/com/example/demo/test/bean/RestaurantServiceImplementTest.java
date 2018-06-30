package com.example.demo.test.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dominios.Restaurant;
import com.example.demo.services.RestaurantService;

public class RestaurantServiceImplementTest implements RestaurantService {

	private static List<Restaurant> restaurants;
	
	public RestaurantServiceImplementTest() {
		super();
		restaurants = new ArrayList<>();
	}

	@Override
	public List<Restaurant> findAll(int userId) {
		List<Restaurant> byUser = new ArrayList<>();
		for (Restaurant restaurant : restaurants) {
			if(restaurant.getUser().getUserId() == userId)
				byUser.add(restaurant);
		}
		return byUser;
	}

	@Override
	public Restaurant findOne(int restaurantId) {
		for (Restaurant restaurant : restaurants) {
			if(restaurant.getRestaurantId() == restaurantId)
				return restaurant;
		}
		return null;
	}

	@Override
	public Restaurant save(Restaurant restaurant) {
		if(restaurant.getRestaurantId() == 0) {
			restaurant.setRestaurantId(restaurants.size() + 1);
			restaurants.add(restaurant);
			return restaurant;
		}else {
			restaurants.set(restaurant.getRestaurantId() - 1, restaurant);
			return restaurant;
		}	
	}

	@Override
	public void delete(int restaurantId) {
		restaurants.remove(restaurantId - 1);

	}

	@Override
	public boolean validateHour(String openHour, String closeHour) {
		int openHourH = Integer.parseInt(openHour.substring(0, 2));
		int closeHourH = Integer.parseInt(closeHour.substring(0, 2));
		int openHourM = Integer.parseInt(openHour.substring(3, 5));
		int closeHourM = Integer.parseInt(closeHour.substring(3, 5));
		String openHourHorarySystem = openHour.substring(6, 8);
		String closeHourHorarySystem = closeHour.substring(6, 8);
		if(openHourHorarySystem.equals("PM")){
				openHourH += 12;
		}
		if(closeHourHorarySystem.equals("PM")){
				closeHourH += 12;
		}
		openHourM += (openHourH *60);
		closeHourM += (closeHourH *60);
		
		return !(openHourM > closeHourM || openHourM == closeHourM);
	}

}
