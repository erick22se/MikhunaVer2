package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominios.Restaurant;;

@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Integer>{

}
