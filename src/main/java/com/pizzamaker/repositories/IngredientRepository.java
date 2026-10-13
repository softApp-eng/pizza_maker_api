package com.pizzamaker.repositories;

import com.pizzamaker.entities.Ingredient;
import com.pizzamaker.entities.enums.IngredientCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findByIsAvailableTrue();
    List<Ingredient> findByCategoryAndIsAvailableTrue(IngredientCategory category);
}