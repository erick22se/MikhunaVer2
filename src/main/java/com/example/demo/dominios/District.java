package com.example.demo.dominios;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "district")
public class District {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int districtId;	
	private String name;
	
	@OneToMany(mappedBy="district")
	private List<Restaurant> restaurants;
	
	public District(int districtId, String name) {
		super();
		this.districtId = districtId;
		this.name = name;
	}
	public District() {
		super();
	}
	public int getDistrictId() {
		return districtId;
	}
	public void setDistrictId(int districtId) {
		this.districtId = districtId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Restaurant> getRestaurants() {
		return restaurants;
	}
	public void setRestaurants(List<Restaurant> restaurants) {
		this.restaurants = restaurants;
	}	
	
}
