package com.pizzamaker.services.impl;

import com.pizzamaker.dtos.OrderItemRequest;
import com.pizzamaker.dtos.OrderRequest;
import com.pizzamaker.dtos.OrderResponse;
import com.pizzamaker.entities.*;
import com.pizzamaker.exceptions.ResourceNotFoundException;
import com.pizzamaker.mappers.DtoMapper;
import com.pizzamaker.repositories.CustomPizzaRepository;
import com.pizzamaker.repositories.OrderRepository;
import com.pizzamaker.repositories.PizzaRepository;
import com.pizzamaker.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final PizzaRepository pizzaRepository;
    private final CustomPizzaRepository customPizzaRepository;
    private final DtoMapper dtoMapper;

    @Override
    public OrderResponse createOrder(OrderRequest request) {
        Order order = Order.builder()
                .customerName(request.getCustomerName())
                .phone(request.getPhone())
                .address(request.getAddress())
                .build();

        List<OrderItem> items = new ArrayList<>();
        BigDecimal total = BigDecimal.ZERO;

        if (request.getItems() != null) {
            for (OrderItemRequest ir : request.getItems()) {
                OrderItem.OrderItemBuilder builder = OrderItem.builder().order(order).quantity(ir.getQuantity() != null ? ir.getQuantity() : 1);
                BigDecimal unitPrice = BigDecimal.ZERO;
                if (ir.getPizzaId() != null) {
                    Pizza pizza = pizzaRepository.findById(ir.getPizzaId())
                            .orElseThrow(() -> new ResourceNotFoundException("Pizza not found: " + ir.getPizzaId()));
                    builder.pizza(pizza);
                    unitPrice = pizza.getBasePrice();
                } else if (ir.getCustomPizzaId() != null) {
                    CustomPizza cp = customPizzaRepository.findById(ir.getCustomPizzaId())
                            .orElseThrow(() -> new ResourceNotFoundException("Custom pizza not found: " + ir.getCustomPizzaId()));
                    builder.customPizza(cp);
                    unitPrice = cp.getTotalPrice();
                }
                builder.unitPrice(unitPrice);
                OrderItem item = builder.build();
                items.add(item);
                total = total.add(unitPrice.multiply(new BigDecimal(item.getQuantity())));
            }
        }
        order.setItems(items);
        order.setTotalPrice(total);
        Order saved = orderRepository.save(order);
        return dtoMapper.toOrderResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public OrderResponse getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Order not found: " + id));
        return dtoMapper.toOrderResponse(order);
    }
}