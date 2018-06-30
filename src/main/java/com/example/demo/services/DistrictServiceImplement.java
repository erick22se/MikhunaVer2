package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dominios.District;
import com.example.demo.repositories.DistrictRepository;

@Service
public class DistrictServiceImplement implements DistrictService{

	@Autowired
	public DistrictRepository repository;
	
	@Override
	public List<District> findAll() {		
		return repository.findAll();
	}

	@Override
	public District findOne(int districtId) {
		return repository.findById(districtId).orElse(null);
	}

	@Override
	public District save(District district) {
		return repository.save(district);
	}

	@Override
	public void delete(int districtId) {
		repository.deleteById(districtId);		
	}

}
