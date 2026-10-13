package com.pizzamaker.services;

import com.pizzamaker.dtos.PizzaDTO;

import java.util.List;

public interface PizzaService {
    List<PizzaDTO> getAllActivePizzas();
    PizzaDTO getPizzaById(Long id);
}