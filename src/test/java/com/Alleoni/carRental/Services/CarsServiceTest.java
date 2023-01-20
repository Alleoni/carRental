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

import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.repositories.CarsRepository;
import com.Alleoni.carRental.services.CarsService;

@ExtendWith(MockitoExtension.class)
public class CarsServiceTest {

	@Mock
	private CarsRepository repository;

	private CarsService carsService;

	@Captor
	private ArgumentCaptor<Cars> captor;

	@Test
	@DisplayName("Find All Test")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideCars());

		var cars = carsService.findAll();

		Assertions.assertEquals(1, cars.size());

	}

	@Test
	@DisplayName("Find By ID Test")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCars().get(0)));

		var car = carsService.findById(1L);

		Assertions.assertEquals(1L, car.getId());
		Assertions.assertEquals(500, car.getDailyRate());

	}

	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCars().get(0)));

		var car = new Cars();
		car.setDailyRate(1000);
		car.setDescription("4 portas");

		carsService.update(1L,car);

		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals(1000, captor.getValue().getDailyRate());
		Assertions.assertEquals("4 portas", captor.getValue().getDescription());

	}

	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {

		carsService.delete(1L);

		Mockito.verify(repository).deleteById(1L);
	}

	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {

		carsService.insert(provideCars().get(0));

		Mockito.verify(repository).save(any());

	}

	private List<Cars> provideCars() {
		var car = new Cars();
		car.setId(1L);
		car.setDailyRate(500);

		var listCars = new ArrayList<Cars>();
		listCars.add(car);

		return listCars;
	}

}
