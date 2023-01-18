package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.repositories.CarsRepository;

@Service
public class CarsService {

	@Autowired
	private CarsRepository repository;

	public List<Cars> findAll() {
		return repository.findAll();
	}

	public Cars findById(Long id) {
		Optional<Cars> obj = repository.findById(id);
		return obj.get();
	}

	public Long create(Cars car) {
		car.setCreatedAt();
		Cars carSaved = repository.save(car);
		return carSaved.getId();
	}

	public void update(Cars car) {
		if (car.getId() == null) {
			throw new NullPointerException("Car Id is undefined");
		}

		Cars carSaved = findById(car.getId());
		updateData(carSaved, car);
		repository.save(carSaved);

	}

	private void updateData(Cars carSaved, Cars car) {
		carSaved.setDescription(car.getDescription() == null ? null : car.getDescription());
		carSaved.setName(car.getName() == null ? null : car.getName());
		carSaved.setAvailable(car.isAvailable());
		carSaved.setCategory(car.getCategory() == null ? null : car.getCategory());
		carSaved.setDailyRate(car.getDailyRate() == null ? null : car.getDailyRate());
		carSaved.setBrand(car.getBrand() == null ? null :  car.getBrand());
		
	}

	public void delete(Long id) {
		repository.deleteById(id);

	}
}
