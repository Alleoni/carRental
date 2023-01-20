package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Specifications;
import com.Alleoni.carRental.repositories.SpecificationsRepository;

@Service
public class SpecificationsService {

	@Autowired
	private SpecificationsRepository repository;

	public List<Specifications> findAll() {
		return repository.findAll();
	}

	public Specifications findById(Long id) {
		Optional<Specifications> obj = repository.findById(id);
		return obj.get();
	}

	public Specifications insert(Specifications specification) {
		specification.setCreatedAt();
//		Specifications specificationSaved = repository.save(specification);
		return repository.save(specification);
	}

	public Specifications update(Long id, Specifications specification) {
		if (specification.getId() == null) {
			throw new NullPointerException("Specifications Id undefined");
		}
		
		Specifications specificationSaved = repository.getReferenceById(id);
		updateData(specificationSaved, specification);
		return repository.save(specificationSaved);

//		Specifications specificationSaved = findById(specification.getId());
//		updateData(specificationSaved, specification);
//		repository.save(specificationSaved);

	}

	private void updateData(Specifications specificationSaved, Specifications specification) {
		specificationSaved.setName(specification.getName() == null ? null : specification.getName());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}
