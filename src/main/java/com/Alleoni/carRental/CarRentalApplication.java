package com.Alleoni.carRental;

import com.Alleoni.carRental.views.HomeView;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CarRentalApplication {

    public static void main(String[] args) {
        HomeView homeView = new HomeView();
        SpringApplication.run(CarRentalApplication.class, args);
        homeView.setVisible(true);

    }

}
