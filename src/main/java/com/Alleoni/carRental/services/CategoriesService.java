package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.repositories.CategoriesRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository repository;

	public List<Categories> findAll() {
		return repository.findAll();
	}

	public Categories findById(Long id) {
		Optional<Categories> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Categories insert(Categories category) {
		category.setCreatedAt();
		return repository.save(category);
	}

	public Categories update(Long id, Categories category) {
		try {
			Categories categorySaved = repository.getReferenceById(id);
			updateData(categorySaved, category);
			return repository.save(categorySaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}
	}

	private void updateData(Categories categorySaved, Categories category) {
		categorySaved.setDescription(category.getDescription() == null ? null : category.getDescription());
		categorySaved.setName(category.getName() == null ? null : category.getName());
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

	public CategoriesService(CategoriesRepository repository) {
		this.repository = repository;
	}
}
