package com.Alleoni.carRental.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.Alleoni.carRental.entities.Customers;
import com.Alleoni.carRental.services.CustomersService;

@RestController
@RequestMapping(value = "/customers")
public class CustomersResource {

	@Autowired
	private CustomersService service;

	@GetMapping
	public ResponseEntity<List<Customers>> findAll() {
		List<Customers> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Customers> findById(@PathVariable Long id){
		Customers obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Customers> insert(@RequestBody Customers customer){
		customer = service.insert(customer);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(customer.getId()).toUri();
		return ResponseEntity.created(uri).body(customer);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Customers> update(@PathVariable Long id, @RequestBody Customers customer){
		customer = service.update(id, customer);
		return ResponseEntity.ok().body(customer);
	}
	
}

