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

import com.Alleoni.carRental.entities.Customers;
import com.Alleoni.carRental.repositories.CustomersRepository;
import com.Alleoni.carRental.services.CustomersService;

@ExtendWith(MockitoExtension.class)
public class CustomersServiceTest {

	@Mock
	private CustomersRepository repository;
	
	private CustomersService customersService;
	
	@Captor
	private ArgumentCaptor<Customers> captor;
	
	@Test
	@DisplayName("Find All Test")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideCustomers());
		
		var customers = customersService.findAll();
		
		Assertions.assertEquals(1, customers.size());
		
	}
	
	@Test
	@DisplayName("Find By ID Test")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCustomers().get(0)) );
		
		var customer = customersService.findById(1L);
		
		Assertions.assertEquals(1L, customer.getId());
		Assertions.assertEquals("CustomerTest", customer.getName());
		Assertions.assertEquals("NewAdress", customer.getAdress());
		Assertions.assertEquals(111111111, customer.getDriverLicense());	
	
	}
	
	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCustomers().get(0)) );
		
		var customer = new Customers();
		customer.setName("newName");
		customer.setEmail("newEmail");
		customer.setAdress("newAdress");
		
		customersService.update(1L,customer);
		
		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals("newName",captor.getValue().getName());
		Assertions.assertEquals("newEmail",captor.getValue().getEmail());
		Assertions.assertEquals("newAdress",captor.getValue().getAdress());
		
	}
	
	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {
	
		customersService.delete(1L);
		
		Mockito.verify(repository).deleteById(1L);
	}
	
	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {
	
		customersService.insert(provideCustomers().get(0));
		
		Mockito.verify(repository).save(any( ));
	
	}
	
	private List<Customers> provideCustomers(){
		var customer = new Customers();
		customer.setName("CustomerTest");
		customer.setId(1L);
		customer.setAdress("NewAdress");
		customer.setDriverLicense(111111111);
		
		var listCustomers = new ArrayList<Customers>();
		listCustomers.add(customer);
		
		return listCustomers;
	}
	
}
