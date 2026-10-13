package com.pizzamaker.dtos;

import com.pizzamaker.entities.enums.IngredientCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class IngredientDTO {
    private Long id;
    private String name;
    private IngredientCategory category;
    private BigDecimal price;
    private Boolean isAvailable;
    private Integer maxQuantity;
    private String imageUrl;
}