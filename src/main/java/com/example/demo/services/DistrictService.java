package com.example.demo.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dominios.District;

@Service
public interface DistrictService {
	
	public List<District> findAll();
	
	public District findOne(int districtId);
	
	public District save(District district);
	
	public void delete(int districtId);
	
}
