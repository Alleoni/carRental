package com.Alleoni.carRental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alleoni.carRental.entities.Cars;

public interface CarsRepository extends JpaRepository<Cars, Long> {

	
	
}
