package com.pizzamaker.controllers;

import com.pizzamaker.dtos.RecommendationDTO;
import com.pizzamaker.dtos.RecommendationRequest;
import com.pizzamaker.services.RecommendationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recommendations")
@RequiredArgsConstructor
@Tag(name = "Recommandations", description = "Système de recommandations")
public class RecommendationController {
    private final RecommendationService recommendationService;

    @PostMapping
    @Operation(summary = "Obtenir des recommandations selon les ingrédients sélectionnés")
    public ResponseEntity<List<RecommendationDTO>> getRecommendations(@RequestBody RecommendationRequest request) {
        return ResponseEntity.ok(recommendationService.getRecommendationsForIngredients(request));
    }

    @GetMapping("/popular")
    @Operation(summary = "Obtenir des recommandations populaires")
    public ResponseEntity<List<RecommendationDTO>> getPopularRecommendations() {
        return ResponseEntity.ok(recommendationService.getPopularRecommendations());
    }
}