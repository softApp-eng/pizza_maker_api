package com.pizzamaker.mappers;

import com.pizzamaker.dtos.CustomPizzaIngredientDTO;
import com.pizzamaker.dtos.CustomPizzaResponse;
import com.pizzamaker.dtos.OrderItemResponse;
import com.pizzamaker.dtos.OrderResponse;
import com.pizzamaker.entities.CustomPizza;
import com.pizzamaker.entities.Order;
import com.pizzamaker.entities.OrderItem;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class DtoMapper {

    public CustomPizzaResponse toCustomPizzaResponse(CustomPizza customPizza) {
        List<CustomPizzaIngredientDTO> ingredients = customPizza.getIngredients().stream()
                .map(i -> CustomPizzaIngredientDTO.builder()
                        .ingredientId(i.getIngredient().getId())
                        .ingredientName(i.getIngredient().getName())
                        .price(i.getIngredient().getPrice())
                        .quantity(i.getQuantity())
                        .build())
                .collect(Collectors.toList());

        return CustomPizzaResponse.builder()
                .id(customPizza.getId())
                .name(customPizza.getName())
                .size(customPizza.getSize())
                .crust(customPizza.getCrust())
                .totalPrice(customPizza.getTotalPrice())
                .createdAt(customPizza.getCreatedAt())
                .ingredients(ingredients)
                .build();
    }

    public OrderResponse toOrderResponse(Order order) {
        List<OrderItemResponse> items = order.getItems().stream()
                .map(this::toOrderItemResponse)
                .collect(Collectors.toList());

        return OrderResponse.builder()
                .id(order.getId())
                .customerName(order.getCustomerName())
                .phone(order.getPhone())
                .address(order.getAddress())
                .status(order.getStatus())
                .totalPrice(order.getTotalPrice())
                .createdAt(order.getCreatedAt())
                .items(items)
                .build();
    }

    private OrderItemResponse toOrderItemResponse(OrderItem item) {
        return OrderItemResponse.builder()
                .id(item.getId())
                .pizzaId(item.getPizza() != null ? item.getPizza().getId() : null)
                .pizzaName(item.getPizza() != null ? item.getPizza().getName() : null)
                .customPizzaId(item.getCustomPizza() != null ? item.getCustomPizza().getId() : null)
                .customPizzaName(item.getCustomPizza() != null ? item.getCustomPizza().getName() : null)
                .quantity(item.getQuantity())
                .unitPrice(item.getUnitPrice())
                .build();
    }
}