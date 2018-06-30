package com.example.demo.test.bean;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.dominios.District;
import com.example.demo.services.DistrictService;

public class DistrictServiceImplementTest implements DistrictService {
	
	private static List<District> districts;
	
	

	public DistrictServiceImplementTest() {
		super();
		districts = new ArrayList<>();
	}

	@Override
	public List<District> findAll() {
		return districts;
	}

	@Override
	public District findOne(int districtId) {
		for (District district : districts) {
			if(district.getDistrictId() == districtId)
				return district;
		}
		return null;
	}

	@Override
	public District save(District district) {
				
		if(district.getDistrictId() == 0) {
			district.setDistrictId(districts.size() + 1);
			districts.add(district);
			return district;
		}else {
			districts.set(district.getDistrictId() - 1, district);
			return district;
		}	
	}

	@Override
	public void delete(int districtId) {		
		districts.remove(districtId - 1);
	}

}
