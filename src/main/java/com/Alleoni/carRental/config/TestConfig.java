package com.Alleoni.carRental.config;


import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.Alleoni.carRental.entities.Brand;
import com.Alleoni.carRental.entities.Cars;
import com.Alleoni.carRental.entities.Categories;
import com.Alleoni.carRental.repositories.BrandRepository;
import com.Alleoni.carRental.repositories.CarsRepository;
import com.Alleoni.carRental.repositories.CategoriesRepository;
//import com.Alleoni.carRental.repositories.CustomersRepository;
//import com.Alleoni.carRental.repositories.RentalsRepository;

@Configuration
@Profile("teste")
public class TestConfig implements CommandLineRunner {
	
	@Autowired
	private CarsRepository carsRepository;
        
        @Autowired
	private CategoriesRepository categoriesRepository;
        
        @Autowired
	private BrandRepository brandRepository;
        
//        @Autowired
//	private CustomersRepository customersRepository;
        
//        @Autowired
//	private RentalsRepository rentalsRepository;

	@Override
	public void run(String... args) throws Exception {
		
		Categories cat1 = new Categories() ;
                cat1.setName("SUV");
                
                Brand brand1 = new Brand();
		Brand brand2 = new Brand();
                
                brand1.setName("Honda");
                brand2.setName("Toyota");
                
		Cars car1 = new Cars();
		Cars car2 = new Cars();
		Cars car3 = new Cars();
                
                car1.setBrand(brand2);
                car2.setColor("Preto");
                car3.setName("Lorem Ipsum");
		
                categoriesRepository.save(cat1);
                brandRepository.saveAll(Arrays.asList(brand1,brand2));
		carsRepository.saveAll(Arrays.asList(car1,car2,car3));
		
	}
	
	
}
