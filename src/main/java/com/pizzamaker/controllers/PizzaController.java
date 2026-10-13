package com.pizzamaker.controllers;

import com.pizzamaker.dtos.PizzaDTO;
import com.pizzamaker.services.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@Tag(name = "Pizzas", description = "Gestion des pizzas prédéfinies")
public class PizzaController {
    private final PizzaService pizzaService;

    @GetMapping
    @Operation(summary = "Lister toutes les pizzas actives")
    public ResponseEntity<List<PizzaDTO>> getAllPizzas() {
        return ResponseEntity.ok(pizzaService.getAllActivePizzas());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtenir une pizza par ID")
    public ResponseEntity<PizzaDTO> getPizzaById(@PathVariable Long id) {
        return ResponseEntity.ok(pizzaService.getPizzaById(id));
    }
}