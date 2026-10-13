package com.pizzamaker.dtos;

import com.pizzamaker.entities.enums.CrustType;
import com.pizzamaker.entities.enums.PizzaSize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPizzaResponse {
    private Long id;
    private String name;
    private PizzaSize size;
    private CrustType crust;
    private BigDecimal totalPrice;
    private LocalDateTime createdAt;
    private List<CustomPizzaIngredientDTO> ingredients;
}