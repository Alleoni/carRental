package com.Alleoni.carRental.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.Alleoni.carRental.entities.Categories;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriesRepository extends JpaRepository<Categories, Long> {
}
