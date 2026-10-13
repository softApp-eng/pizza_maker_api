package com.pizzamaker.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    private Long pizzaId;
    private Long customPizzaId;
    private Integer quantity;
}