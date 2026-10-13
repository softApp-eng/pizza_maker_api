package com.pizzamaker.controllers;

import com.pizzamaker.dtos.IngredientDTO;
import com.pizzamaker.entities.enums.IngredientCategory;
import com.pizzamaker.services.IngredientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ingredients")
@RequiredArgsConstructor
@Tag(name = "Ingrédients", description = "Catalogue des ingrédients")
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping
    @Operation(summary = "Lister tous les ingrédients disponibles")
    public ResponseEntity<List<IngredientDTO>> getAllIngredients(@RequestParam(required = false) IngredientCategory category) {
        if (category != null) {
            return ResponseEntity.ok(ingredientService.getIngredientsByCategory(category));
        }
        return ResponseEntity.ok(ingredientService.getAllAvailableIngredients());
    }
}