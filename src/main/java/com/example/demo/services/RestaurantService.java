package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dominios.Restaurant;

@Service
public interface RestaurantService {
	
	public List<Restaurant> findAll(int userId);
	
	public Restaurant findOne(int restaurantId);
	
	public Restaurant save(Restaurant restaurant);
	
	public void delete(int restaurantId);
	
	public boolean validateHour(String openHour, String closeHour);

}
