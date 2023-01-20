package com.Alleoni.carRental.entities;

import java.io.Serializable;
import java.time.Instant;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_Cars")
public class Cars implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String description;
	private Integer dailyRate;
	private boolean available;
	private String licensePlate;
	private Instant createdAt;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Categories category;

	@ManyToOne
	@JoinColumn(name = "brand_id")
	private Brand brand;
	
	@ManyToOne
	@JoinColumn(name = "specs_id")
	private Specifications specification;

	@OneToOne
	private Rentals rental;

	@OneToOne
	private CarsImages carImage;

	public Cars() {

	}

	public Cars(Long id, String name, String description, Integer dailyRate, boolean available, String licensePlate,
			Instant createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.dailyRate = dailyRate;
		this.available = available;
		this.licensePlate = licensePlate;
		this.createdAt = createdAt;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDailyRate() {
		return dailyRate;
	}

	public void setDailyRate(Integer dailyRate) {
		this.dailyRate = dailyRate;
	}

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public String getLicensePlate() {
		return licensePlate;
	}

	public void setLicensePlate(String licensePlate) {
		this.licensePlate = licensePlate;
	}

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = Instant.now();
	}

	public Categories getCategory() {
		return category;
	}

	public void setCategory(Categories category) {
		this.category = category;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public Rentals getRental() {
		return rental;
	}

	public void setRental(Rentals rental) {
		this.rental = rental;
	}

	public CarsImages getCarImage() {
		return carImage;
	}

	public void setCarImage(CarsImages carImage) {
		this.carImage = carImage;
	}
	
	

}
