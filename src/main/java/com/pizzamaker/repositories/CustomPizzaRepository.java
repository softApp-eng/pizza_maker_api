package com.pizzamaker.repositories;

import com.pizzamaker.entities.CustomPizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomPizzaRepository extends JpaRepository<CustomPizza, Long> {
}