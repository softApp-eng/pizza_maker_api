package com.pizzamaker.services;

import com.pizzamaker.dtos.CustomPizzaRequest;
import com.pizzamaker.dtos.CustomPizzaResponse;

public interface CustomPizzaService {
    CustomPizzaResponse createCustomPizza(CustomPizzaRequest request);
    CustomPizzaResponse getCustomPizzaById(Long id);
}