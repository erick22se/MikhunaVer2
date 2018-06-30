package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.dominios.District;


@Repository
public interface DistrictRepository extends JpaRepository<District, Integer>{

}
