package com.pizzamaker.dtos;

import com.pizzamaker.entities.enums.CrustType;
import com.pizzamaker.entities.enums.PizzaSize;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomPizzaRequest {
    private String name;
    private PizzaSize size;
    private CrustType crust;
    private List<CustomPizzaIngredientRequest> ingredients;
}