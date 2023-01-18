package com.Alleoni.carRental.Services;


import static org.mockito.ArgumentMatchers.anyLong;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.Alleoni.carRental.entities.Brand;
import com.Alleoni.carRental.repositories.BrandRepository;
import com.Alleoni.carRental.services.BrandService;

@ExtendWith(MockitoExtension.class)
public class BrandServiceTest {

	@Mock
	private BrandRepository repository;
	
	private BrandService brandService;
	
	@Captor
	private ArgumentCaptor<Brand> captor;
	
	@Test
	@DisplayName("Find All Teste")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideBrand());
		
		var brand = brandService.findAll();
		
		Assertions.assertEquals(1, brand.size());
		
	}
	
	@Test
	@DisplayName("Find By Id Teste")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideBrand().get(0)) );
		
		var brand = brandService.findById(1L);
		
		Assertions.assertEquals(1L, brand.getId());
		Assertions.assertEquals("CategoryTest", brand.getName());
	}
	
	@Test
	@DisplayName("Test 3")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideBrand().get(0)) );
		
		var brand = new Brand();
		brand.setName("newName");
		
		brandService.update(brand);
		
		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals("newName",captor.getValue().getName());
		
	}
	
	@Test
	@DisplayName("Test 4")
	private void shouldRunSucessfullyDelete() {
	
		brandService.delete(1L);
		
		Mockito.verify(repository).deleteById(1L);
	}
	
	private List<Brand> provideBrand(){
		var brand = new Brand();
		brand.setName("CategoryTest");
		brand.setId(1L);
		
		var listBrand = new ArrayList<Brand>();
		listBrand.add(brand);
		
		return listBrand;
	}
	
}
