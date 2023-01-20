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

import com.Alleoni.carRental.entities.CarsImages;
import com.Alleoni.carRental.services.CarsImagesService;

@RestController
@RequestMapping(value = "/carsImages")
public class CarsImagesResource {

	@Autowired
	private CarsImagesService service;

	@GetMapping
	public ResponseEntity<List<CarsImages>> findAll() {
		List<CarsImages> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<CarsImages> findById(@PathVariable Long id){
		CarsImages obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<CarsImages> insert(@RequestBody CarsImages carsImage){
		carsImage = service.insert(carsImage);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(carsImage.getId()).toUri();
		return ResponseEntity.created(uri).body(carsImage);
	}
	
	@DeleteMapping(value = "{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
		
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<CarsImages> update(@PathVariable Long id, @RequestBody CarsImages carsImage){
		carsImage = service.update(id, carsImage);
		return ResponseEntity.ok().body(carsImage);
	}
	
}

