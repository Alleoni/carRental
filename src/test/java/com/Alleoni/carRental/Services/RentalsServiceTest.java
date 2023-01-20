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

import com.Alleoni.carRental.entities.Rentals;
import com.Alleoni.carRental.repositories.RentalsRepository;
import com.Alleoni.carRental.services.RentalsService;

@ExtendWith(MockitoExtension.class)
public class RentalsServiceTest {

	@Mock
	private RentalsRepository repository;

	private RentalsService rentalsService;

	@Captor
	private ArgumentCaptor<Rentals> captor;

	@Test
	@DisplayName("Find All Test")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideRentals());

		var rentals = rentalsService.findAll();

		Assertions.assertEquals(1, rentals.size());

	}

	@Test
	@DisplayName("Find By ID Test")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideRentals().get(0)));

		var rental = rentalsService.findById(1L);

		Assertions.assertEquals(1L, rental.getId());

	}

	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideRentals().get(0)));

		var rental = new Rentals();
		rental.setStartDate(rental.getStartDate());

		rentalsService.update(1L,rental);

		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals(rental.getStartDate(), captor.getValue().getStartDate());

	}

	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {

		rentalsService.delete(1L);

		Mockito.verify(repository).deleteById(1L);
	}

	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {

		rentalsService.insert(provideRentals().get(0));

		Mockito.verify(repository).save(any());

	}

	private List<Rentals> provideRentals() {
		var rental = new Rentals();
		rental.setId(1L);

		var listRentals = new ArrayList<Rentals>();
		listRentals.add(rental);

		return listRentals;
	}

}
