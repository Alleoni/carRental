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

import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.services.CategoriesService;

@RestController
@RequestMapping(value = "/categories")
public class CategoriesResource {

	@Autowired
	private CategoriesService service;

	@GetMapping
	public ResponseEntity<List<Categories>> findAll() {
		List<Categories> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<Categories> findById(@PathVariable Long id){
		Categories obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Categories> insert(@RequestBody Categories category){
		category = service.insert(category);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(category.getId()).toUri();
		return ResponseEntity.created(uri).body(category);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<Categories> update(@PathVariable Long id, @RequestBody Categories category){
		category = service.update(id, category);
		return ResponseEntity.ok().body(category);
	}
	
}

