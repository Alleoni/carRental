package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.repositories.CarsRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

import org.springframework.stereotype.Service;

@Service
public class CarsService {

	@Autowired
	private CarsRepository repository;

	public List<Cars> findAll() {
		return repository.findAll();
	}

	public Cars findById(Long id) {
		Optional<Cars> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Cars insert(Cars car) {
		car.setCreatedAt();
		return repository.save(car);
	}

	public Cars update(Long id, Cars car) {
		try {
			Cars carSaved = repository.getReferenceById(id);
			updateData(carSaved, car);
			return repository.save(carSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Cars carSaved, Cars car) {
		carSaved.setDescription(car.getDescription() == null ? null : car.getDescription());
		carSaved.setName(car.getName() == null ? null : car.getName());
		carSaved.setAvailable(car.isAvailable());
		carSaved.setCategory(car.getCategory() == null ? null : car.getCategory());
		carSaved.setDailyRate(car.getDailyRate() == null ? null : car.getDailyRate());
		carSaved.setBrand(car.getBrand() == null ? null : car.getBrand());

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
