package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.CarsImages;
import com.Alleoni.carRental.repositories.CarsImagesRepository;

@Service
public class CarsImagesService {

	@Autowired
	private CarsImagesRepository repository;

	public List<CarsImages> findAll() {
		return repository.findAll();
	}

	public CarsImages findById(Long id) {
		Optional<CarsImages> obj = repository.findById(id);
		return obj.get();
	}

	public Long create(CarsImages carImage) {
		carImage.setCreatedAt();
		CarsImages carImageSaved = repository.save(carImage);
		return carImageSaved.getId();
	}

	public void update(CarsImages carImage) {
		if (carImage.getId() == null) {
			throw new NullPointerException("CarsImages Id undefined");
		}

		CarsImages carImageSaved = findById(carImage.getId());
		updateData(carImageSaved, carImage);
		repository.save(carImageSaved);

	}

	private void updateData(CarsImages carImageSaved, CarsImages carImage) {
		carImageSaved.setImage(carImage.getImage() == null ? null : carImage.getImage());
	}

	public void delete(Long id) {
		repository.deleteById(id);
	}
}