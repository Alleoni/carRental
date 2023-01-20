package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Rentals;
import com.Alleoni.carRental.repositories.RentalsRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class RentalsService {

	@Autowired
	private RentalsRepository repository;

	public List<Rentals> findAll() {
		return repository.findAll();
	}

	public Rentals findById(Long id) {
		Optional<Rentals> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Rentals insert(Rentals rental) {
		rental.setCreatedAt();
		return repository.save(rental);
	}

	public Rentals update(Long id, Rentals rental) {
		try {
			Rentals rentalSaved = repository.getReferenceById(id);
			updateData(rentalSaved, rental);
			return repository.save(rentalSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Rentals rentalSaved, Rentals rental) {
		rentalSaved.setStartDate(rental.getStartDate() == null ? null : rental.getStartDate());
		rentalSaved.setEndDate(rental.getEndDate() == null ? null : rental.getEndDate());
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException(id);
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException(e.getMessage());
		}
	}
}
