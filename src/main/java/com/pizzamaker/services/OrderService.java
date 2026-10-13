package com.pizzamaker.services;

import com.pizzamaker.dtos.OrderRequest;
import com.pizzamaker.dtos.OrderResponse;

public interface OrderService {
    OrderResponse createOrder(OrderRequest request);
    OrderResponse getOrderById(Long id);
}