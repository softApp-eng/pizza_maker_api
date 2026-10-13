package com.pizzamaker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPizzaIngredientRequest {
    private Long ingredientId;
    private Integer quantity;
}