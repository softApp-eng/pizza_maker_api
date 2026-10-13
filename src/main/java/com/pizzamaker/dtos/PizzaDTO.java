package com.pizzamaker.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PizzaDTO {
    private Long id;
    private String name;
    private String description;
    private String imageUrl;
    private BigDecimal basePrice;
    private Boolean isCustom;
    private List<IngredientDTO> ingredients;
}