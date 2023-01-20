package com.Alleoni.carRental.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Alleoni.carRental.entities.Customers;
import com.Alleoni.carRental.repositories.CustomersRepository;

@Service
public class CustomersService {

	@Autowired
	private CustomersRepository repository;
	
	public List<Customers> findAll(){
		return repository.findAll();
	}
	
	public Customers findById(Long id) {
		Optional<Customers> obj = repository.findById(id);
		return obj.get();
	}
	
	public Customers insert(Customers customer) {
		customer.setCreatedAt();
//		Customers customerSaved = repository.save(customer);
		return repository.save(customer);
	}
	
	public Customers update(Long id, Customers customer) {
		if (customer.getId() == null) {
			throw new NullPointerException("Brand Id undefined");
		}
		
		Customers customerSaved = repository.getReferenceById(id);
		updateData(customerSaved, customer);
		return repository.save(customerSaved);
		
//		Customers customerSaved = findById(customer.getId());
//		updateData(customerSaved, customer);
//		customer.setUpdateAt();
//		return repository.save(customerSaved);
	
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
		repository.deleteById(id);
	}
	
}
