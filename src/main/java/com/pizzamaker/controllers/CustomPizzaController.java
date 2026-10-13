package com.pizzamaker.controllers;

import com.pizzamaker.dtos.CustomPizzaRequest;
import com.pizzamaker.dtos.CustomPizzaResponse;
import com.pizzamaker.services.CustomPizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/custom-pizzas")
@RequiredArgsConstructor
@Tag(name = "Pizzas personnalisées", description = "Création de pizzas personnalisées")
public class CustomPizzaController {
    private final CustomPizzaService customPizzaService;

    @PostMapping
    @Operation(summary = "Créer une pizza personnalisée")
    public ResponseEntity<CustomPizzaResponse> createCustomPizza(@Valid @RequestBody CustomPizzaRequest request) {
        return ResponseEntity.ok(customPizzaService.createCustomPizza(request));
    }

    @GetMapping("/{id}")
    @Operation(summary = "Récupérer une pizza personnalisée")
    public ResponseEntity<CustomPizzaResponse> getCustomPizza(@PathVariable Long id) {
        return ResponseEntity.ok(customPizzaService.getCustomPizzaById(id));
    }
}