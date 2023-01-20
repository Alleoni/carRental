package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Specifications;
import com.Alleoni.carRental.repositories.SpecificationsRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SpecificationsService {

	@Autowired
	private SpecificationsRepository repository;

	public List<Specifications> findAll() {
		return repository.findAll();
	}

	public Specifications findById(Long id) {
		Optional<Specifications> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Specifications insert(Specifications specification) {
		specification.setCreatedAt();
		return repository.save(specification);
	}

	public Specifications update(Long id, Specifications specification) {
		try {
			Specifications specificationSaved = repository.getReferenceById(id);
			updateData(specificationSaved, specification);
			return repository.save(specificationSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Specifications specificationSaved, Specifications specification) {
		specificationSaved.setName(specification.getName() == null ? null : specification.getName());
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
