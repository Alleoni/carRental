package com.Alleoni.carRental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alleoni.carRental.entities.Rentals;

public interface RentalsRepository extends JpaRepository<Rentals, Long> {

	
	
}
