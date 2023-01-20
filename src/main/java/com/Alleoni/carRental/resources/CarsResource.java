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

import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.services.CarsService;

@RestController
@RequestMapping(value = "/cars")
public class CarsResource {

	@Autowired
	private CarsService service;

	@GetMapping
	public ResponseEntity<List<Cars>> findAll() {
		List<Cars> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Cars> findById(@PathVariable Long id){
		Cars obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Cars> insert(@RequestBody Cars car){
		car = service.insert(car);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(car.getId()).toUri();
		return ResponseEntity.created(uri).body(car);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Cars> update(@PathVariable Long id, @RequestBody Cars car){
		car = service.update(id, car);
		return ResponseEntity.ok().body(car);
	}
	
}

