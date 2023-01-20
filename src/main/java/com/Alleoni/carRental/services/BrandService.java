package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Brand;
import com.Alleoni.carRental.repositories.BrandRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class BrandService {

	@Autowired
	private BrandRepository repository;

	public List<Brand> findAll() {
		return repository.findAll();
	}

	public Brand findById(Long id) {
		Optional<Brand> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Brand insert(Brand brand) {
		brand.setCreatedAt();
		return repository.save(brand);
	}

	public Brand update(Long id, Brand brand) {
		try {
			Brand brandSaved = repository.getReferenceById(id);
			updateData(brandSaved, brand);
			return repository.save(brandSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Brand brandSaved, Brand brand) {
		brandSaved.setName(brand.getName() == null ? null : brand.getName());
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
