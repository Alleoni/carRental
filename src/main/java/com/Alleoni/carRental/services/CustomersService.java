package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Customers;
import com.Alleoni.carRental.repositories.CustomersRepository;
import com.Alleoni.carRental.services.exceptions.DatabaseException;
import com.Alleoni.carRental.services.exceptions.ResourceNotFoundException;

import jakarta.persistence.EntityNotFoundException;

@Service
public class CustomersService {

	@Autowired
	private CustomersRepository repository;

	public List<Customers> findAll() {
		return repository.findAll();
	}

	public Customers findById(Long id) {
		Optional<Customers> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ResourceNotFoundException(id));
	}

	public Customers insert(Customers customer) {
		customer.setCreatedAt();
		return repository.save(customer);
	}

	public Customers update(Long id, Customers customer) {
		try {
			Customers customerSaved = repository.getReferenceById(id);
			updateData(customerSaved, customer);
			return repository.save(customerSaved);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException(id);
		}

	}

	private void updateData(Customers customerSaved, Customers customer) {
		customerSaved.setAdress(customer.getAdress() == null ? null : customer.getAdress());
		customerSaved.setDriverLicense(customer.getDriverLicense() == null ? null : customer.getDriverLicense());
		customerSaved.setEmail(customer.getEmail() == null ? null : customer.getEmail());
		customerSaved.setName(customer.getName() == null ? null : customer.getName());
		customerSaved.setPhoneNumber(customer.getPhoneNumber() == null ? null : customer.getPhoneNumber());
		customerSaved.setBirthDate(customer.getBirthDate() == null ? null : customer.getBirthDate());
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
