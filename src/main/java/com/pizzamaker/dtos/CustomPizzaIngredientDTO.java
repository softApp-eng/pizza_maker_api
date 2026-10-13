package com.pizzamaker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPizzaIngredientDTO {
    private Long ingredientId;
    private String ingredientName;
    private BigDecimal price;
    private Integer quantity;
}