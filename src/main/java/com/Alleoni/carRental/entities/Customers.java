package com.Alleoni.carRental.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_customers")
public class Customers implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Instant birthInstant;
	private String email;
	private Double driverLicense;
	private String adress;
	private Double phoneNumber;
	private Instant createdAt;
	private Instant updateAt;
	
	@OneToMany(mappedBy = "customer")
	private List<Rentals> rentals = new ArrayList<>();
	
	
	public Customers() {
		
	}


	public Customers(Long id, String name, java.time.Instant birthInstant, String email, Double driverLicense,
			String adress, Double phoneNumber, java.time.Instant createdAt, java.time.Instant updateAt) {
		super();
		this.id = id;
		this.name = name;
		this.birthInstant = birthInstant;
		this.email = email;
		this.driverLicense = driverLicense;
		this.adress = adress;
		this.phoneNumber = phoneNumber;
		this.createdAt = createdAt;
		this.updateAt = updateAt;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Instant getBirthInstant() {
		return birthInstant;
	}


	public void setBirthInstant(Instant birthInstant) {
		this.birthInstant = birthInstant;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Double getDriverLicense() {
		return driverLicense;
	}


	public void setDriverLicense(Double driverLicense) {
		this.driverLicense = driverLicense;
	}


	public String getAdress() {
		return adress;
	}


	public void setAdress(String adress) {
		this.adress = adress;
	}


	public Double getPhoneNumber() {
		return phoneNumber;
	}


	public void setPhoneNumber(Double phoneNumber) {
		this.phoneNumber = phoneNumber;
	}


	public Instant getCreatedAt() {
		return createdAt;
	}


	public void setCreatedAt(Instant createdAt) {
		this.createdAt = createdAt;
	}


	public Instant getUpdateAt() {
		return updateAt;
	}


	public void setUpdateAt(Instant updateAt) {
		this.updateAt = updateAt;
	}


	public List<Rentals> getRentals() {
		return rentals;
	}


	public void setRentals(List<Rentals> rentals) {
		this.rentals = rentals;
	}
	

}
