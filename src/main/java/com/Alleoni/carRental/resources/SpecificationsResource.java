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

import com.Alleoni.carRental.entities.Specifications;
import com.Alleoni.carRental.services.SpecificationsService;

@RestController
@RequestMapping(value = "/specifications")
public class SpecificationsResource {

	@Autowired
	private SpecificationsService service;

	@GetMapping
	public ResponseEntity<List<Specifications>> findAll() {
		List<Specifications> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Specifications> findById(@PathVariable Long id){
		Specifications obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Specifications> insert(@RequestBody Specifications specifications){
		specifications = service.insert(specifications);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(specifications.getId()).toUri();
		return ResponseEntity.created(uri).body(specifications);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Specifications> update(@PathVariable Long id, @RequestBody Specifications specifications){
		specifications = service.update(id, specifications);
		return ResponseEntity.ok().body(specifications);
	}
	
}

