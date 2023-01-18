package com.Alleoni.carRental.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.repositories.CarsRepository;
import com.Alleoni.carRental.repositories.CategoriesRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

	@Autowired
	private CategoriesRepository categoryRepository;
	
	@Autowired
	private CarsRepository carsRepository;

	@Override
	public void run(String... args) throws Exception {
		
	/*	Categories cat1 = new Categories() ;
		Categories cat2 = new Categories();
	
		categoryRepository.saveAll(Arrays.asList(cat1, cat2));
		
		Cars car1 = new Cars(1L, "Evoke", "Automático", 400, false, "KLB1234", cat1, null);
		Cars car2 = new Cars(2L, "Marea", "Mecânico", 200, true, "BOB4567", cat2, null);
		Cars car3 = new Cars(3L, "Mustang", "2 Portas", 1000, true, "RIC2222", cat2, null);
		
		carsRepository.saveAll(Arrays.asList(car1,car2,car3));
		*/
		
		
		
		
	}
	
	
}
