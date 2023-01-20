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

import com.Alleoni.carRental.entities.Specifications;
import com.Alleoni.carRental.repositories.SpecificationsRepository;
import com.Alleoni.carRental.services.SpecificationsService;

@ExtendWith(MockitoExtension.class)
public class SpecificationsServiceTest {

	@Mock
	private SpecificationsRepository repository;

	private SpecificationsService specificationService;

	@Captor
	private ArgumentCaptor<Specifications> captor;

	@Test
	@DisplayName("Find All Teste")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideSpecifications());

		var specification = specificationService.findAll();

		Assertions.assertEquals(1L, specification.size());

	}

	@Test
	@DisplayName("Find By Id Teste")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideSpecifications().get(0)));

		var specification = specificationService.findById(1L);

		Assertions.assertEquals(1L, specification.getId());
		Assertions.assertEquals("SpecificationsTest", specification.getName());
	}

	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideSpecifications().get(0)));

		var specification = new Specifications();
		specification.setName("newName");

		specificationService.update(1L, specification);

		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals("newName", captor.getValue().getName());

	}

	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {

		specificationService.delete(1L);

		Mockito.verify(repository).deleteById(1L);
	}

	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {

		specificationService.insert(provideSpecifications().get(0));

		Mockito.verify(repository).save(any());

	}

	private List<Specifications> provideSpecifications() {
		var specification = new Specifications();
		specification.setName("SpecificationsTest");
		specification.setId(1L);

		var listSpecifications = new ArrayList<Specifications>();
		listSpecifications.add(specification);

		return listSpecifications;
	}

}
