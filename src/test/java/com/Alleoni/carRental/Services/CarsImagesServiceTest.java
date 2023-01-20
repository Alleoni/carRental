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

import com.Alleoni.carRental.entities.CarsImages;
import com.Alleoni.carRental.repositories.CarsImagesRepository;
import com.Alleoni.carRental.services.CarsImagesService;

@ExtendWith(MockitoExtension.class)
public class CarsImagesServiceTest {

	@Mock
	private CarsImagesRepository repository;

	private CarsImagesService carsImagesService;

	@Captor
	private ArgumentCaptor<CarsImages> captor;

	@Test
	@DisplayName("Find All Teste")
	private void shouldRunSucessfullyFindAll() {
		Mockito.when(repository.findAll()).thenReturn(provideCarsImages());

		var carsImages = carsImagesService.findAll();

		Assertions.assertEquals(1L, carsImages.size());

	}

	@Test
	@DisplayName("Find By Id Teste")
	private void shouldRunSucessfullyFindById() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCarsImages().get(0)));

		var carsImages = carsImagesService.findById(1L);

		Assertions.assertEquals(1L, carsImages.getId());
		Assertions.assertEquals("URL://", carsImages.getImage());
	}

	@Test
	@DisplayName("Update Test")
	private void shouldRunSucessfullyUpdate() {
		Mockito.when(repository.findById(anyLong())).thenReturn(Optional.of(provideCarsImages().get(0)));

		var carsImages = new CarsImages();
		carsImages.setImage("URL://TESTE");

		carsImagesService.update(1L, carsImages);

		Mockito.verify(repository.save(captor.capture()));
		Assertions.assertEquals("URL://TESTE", captor.getValue().getImage());

	}

	@Test
	@DisplayName("Delete Test")
	private void shouldRunSucessfullyDelete() {

		carsImagesService.delete(1L);

		Mockito.verify(repository).deleteById(1L);
	}

	@Test
	@DisplayName("Create Test")
	private void shouldRunSucessfullyCreate() {

		carsImagesService.insert(provideCarsImages().get(0));

		Mockito.verify(repository).save(any());

	}

	private List<CarsImages> provideCarsImages() {
		var carsImages = new CarsImages();
		carsImages.setImage("URL://");
		carsImages.setId(1L);

		var listCarsImages = new ArrayList<CarsImages>();
		listCarsImages.add(carsImages);

		return listCarsImages;
	}

}
