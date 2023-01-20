package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Rentals;
import com.Alleoni.carRental.repositories.RentalsRepository;

@Service
public class RentalsService {

	@Autowired
	private RentalsRepository repository;

	public List<Rentals> findAll() {
		return repository.findAll();
	}

	public Rentals findById(Long id) {
		Optional<Rentals> obj = repository.findById(id);
		return obj.get();
	}

	public Rentals insert(Rentals rental) {
		rental.setCreatedAt();
//		Rentals rentalSaved = repository.save(rental);
		return repository.save(rental);
	}

	public Rentals update(Long id, Rentals rental) {
		if (rental.getId() == null) {
			throw new NullPointerException("Rental Id undefined");
		}

		Rentals rentalSaved = repository.getReferenceById(id);
		updateData(rentalSaved, rental);
		return repository.save(rentalSaved);
		
//		Rentals rentalSaved = findById(rental.getId());
//		updateData(rentalSaved, rental);
//		rental.setUpdatedAt();
//		repository.save(rentalSaved);

	}

	private void updateData(Rentals rentalSaved, Rentals rental) {
		rentalSaved.setStartDate(rental.getStartDate() == null ? null : rental.getStartDate());
		rentalSaved.setEndDate(rental.getEndDate() == null ? null : rental.getEndDate());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
