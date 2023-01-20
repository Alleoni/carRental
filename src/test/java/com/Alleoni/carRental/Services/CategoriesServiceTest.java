package com.Alleoni.carRental.Services;


import static org.mockito.ArgumentMatchers.any;
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

import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.repositories.CategoriesRepository;
import com.Alleoni.carRental.services.CategoriesService;

@ExtendWith(MockitoExtension.class)
public class CategoriesServiceTest {

	@Mock
	private CategoriesRepository repository;
	
	private CategoriesService categoriesService;
	
	@Captor
	private ArgumentCaptor<Categories> captor;
	
	@Test
	@DisplayName("Find All Test")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideCategories());
		
		var categories = categoriesService.findAll();
		
		Assertions.assertEquals(1, categories.size());
		
	}
	
	@Test
	@DisplayName("Find By Id Test")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCategories().get(0)) );
		
		var category = categoriesService.findById(1L);
		
		Assertions.assertEquals(1L, category.getId());
		Assertions.assertEquals("CategoryTest", category.getName());
		Assertions.assertEquals("DescriptionTest", category.getDescription());
	}
	
	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCategories().get(0)) );
		
		var category = new Categories();
		category.setName("newName");
		
		categoriesService.update(1L , category);
		
		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals("newName",captor.getValue().getName());
		
	}
	
	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {
	
		categoriesService.delete(1L);
		
		Mockito.verify(repository).deleteById(1L);
	}
	
	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {
	
		categoriesService.insert(provideCategories().get(0));
		
		Mockito.verify(repository).save(any( ));
	
	}
	
	private List<Categories> provideCategories(){
		var category = new Categories();
		category.setName("CategoryTest");
		category.setDescription("DescriptionTest");
		category.setId(1L);
		
		var listCategories = new ArrayList<Categories>();
		listCategories.add(category);
		
		return listCategories;
	}
	
}
