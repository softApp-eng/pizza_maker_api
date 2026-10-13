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
public class RecommendationDTO {
    private Long suggestedIngredientId;
    private String suggestedIngredientName;
    private BigDecimal suggestedIngredientPrice;
    private Double score;
    private String reason;
}