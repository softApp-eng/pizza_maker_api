package com.pizzamaker.controllers;

import com.pizzamaker.dtos.OrderRequest;
import com.pizzamaker.dtos.OrderResponse;
import com.pizzamaker.services.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
@Tag(name = "Commandes", description = "Gestion des commandes")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @Operation(summary = "Créer une commande")
    public ResponseEntity<OrderResponse> createOrder(@Valid @RequestBody OrderRequest request) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une commande")
    public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }
}