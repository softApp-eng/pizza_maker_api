package com.pizzamaker.services;

import com.pizzamaker.dtos.RecommendationDTO;
import com.pizzamaker.dtos.RecommendationRequest;

import java.util.List;

public interface RecommendationService {
    List<RecommendationDTO> getRecommendationsForIngredients(RecommendationRequest request);
    List<RecommendationDTO> getPopularRecommendations();
}