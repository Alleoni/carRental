package com.Alleoni.carRental.entities;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_brand")
public class Brand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private Instant createdAt;

	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	private List<Cars> cars = new ArrayList<>();

	public Brand() {

	}

	public Brand(Long id, String name, Instant createdAt) {
		super();
		this.id = id;
		this.name = name;
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

	public Instant getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt() {
		this.createdAt = Instant.now();
	}

	public List<Cars> getCars() {
		return cars;
	}

	public void setCars(List<Cars> cars) {
		this.cars = cars;
	}
	

}
