package com.example.demo.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominios.Restaurant;
import com.example.demo.repositories.RestaurantRepository;

@Service
public class RestaurantServiceImplement implements RestaurantService {

	@Autowired
	public RestaurantRepository repository;
	
	@Override
	public List<Restaurant> findAll(int userId) {
		return repository.findAll().stream().filter(x->x.getUser().getUserId() == userId).collect(Collectors.toList());
	}

	@Override
	public Restaurant findOne(int restaurantId) {
		return repository.findById(restaurantId).orElse(null);
	}

	@Override
	public Restaurant save(Restaurant restaurant) {
		return repository.save(restaurant);
		
	}

	@Override
	public void delete(int restaurantId) {
		repository.deleteById(restaurantId);		
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
