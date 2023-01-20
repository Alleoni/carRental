package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.CarsImages;
import com.Alleoni.carRental.repositories.CarsImagesRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CarsImagesService {

	@Autowired
	private CarsImagesRepository repository;

	public List<CarsImages> findAll() {
		return repository.findAll();
	}

	public CarsImages findById(Long id) {
		Optional<CarsImages> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public CarsImages insert(CarsImages carImage) {
		carImage.setCreatedAt();
		return repository.save(carImage);
	}

	public CarsImages update(Long id, CarsImages carImage) {
		try {
			CarsImages carImageSaved = repository.getReferenceById(id);
			updateData(carImageSaved, carImage);
			return repository.save(carImageSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(CarsImages carImageSaved, CarsImages carImage) {
		carImageSaved.setImage(carImage.getImage() == null ? null : carImage.getImage());
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
