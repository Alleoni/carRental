package com.Alleoni.carRental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alleoni.carRental.entities.Customers;

public interface CustomersRepository extends JpaRepository<Customers, Long> {

	
	
}
