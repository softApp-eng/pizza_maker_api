package com.pizzamaker.services;

import com.pizzamaker.dtos.IngredientDTO;
import com.pizzamaker.entities.enums.IngredientCategory;

import java.util.List;

public interface IngredientService {
    List<IngredientDTO> getAllAvailableIngredients();
    List<IngredientDTO> getIngredientsByCategory(IngredientCategory category);
}