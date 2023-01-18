package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Brand;
import com.Alleoni.carRental.repositories.BrandRepository;

@Service
public class BrandService {

	@Autowired
	private BrandRepository repository;

	public List<Brand> findAll() {
		return repository.findAll();
	}

	public Brand findById(Long id) {
		Optional<Brand> obj = repository.findById(id);
		return obj.get();
	}

	public Long create(Brand brand) {
		brand.setCreatedAt();
		Brand brandSaved = repository.save(brand);
		return brandSaved.getId();
	}

	public void update(Brand brand) {
		if (brand.getId() == null) {
			throw new NullPointerException("Brand Id undefined");
		}

		Brand brandSaved = findById(brand.getId());
		updateData(brandSaved, brand);
		repository.save(brandSaved);

	}

	private void updateData(Brand brandSaved, Brand brand) {
		brandSaved.setName(brand.getName() == null ? null : brand.getName());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
