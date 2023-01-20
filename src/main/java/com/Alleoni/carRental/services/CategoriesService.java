package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.repositories.CategoriesRepository;

@Service
public class CategoriesService {

	@Autowired
	private CategoriesRepository repository;

	public List<Categories> findAll() {
		return repository.findAll();
	}

	public Categories findById(Long id) {
		Optional<Categories> obj = repository.findById(id);
		return obj.get();
	}

	public Categories insert(Categories category) {
		category.setCreatedAt();
//		Categories categorySaved = repository.save(category);
		return repository.save(category);
	}

	public Categories update(Long id, Categories category) {
		if (category.getId() == null) {
			throw new NullPointerException("Category Id undefined");
		}
		Categories categorySaved = repository.getReferenceById(id);
		updateData(categorySaved, category);
		return repository.save(categorySaved);

//		Categories categorySaved = findById(category.getId());
//		updateData(categorySaved, category);
//		repository.save(categorySaved);
	}

	private void updateData(Categories categorySaved, Categories category) {
		categorySaved.setDescription(category.getDescription() == null ? null : category.getDescription());
		categorySaved.setName(category.getName() == null ? null : category.getName());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}

	public CategoriesService(CategoriesRepository repository) {
		this.repository = repository;
	}
}
