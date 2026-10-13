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
public class OrderItemResponse {
    private Long id;
    private Long pizzaId;
    private String pizzaName;
    private Long customPizzaId;
    private String customPizzaName;
    private Integer quantity;
    private BigDecimal unitPrice;
}