package com.pizzamaker.services.impl;

import com.pizzamaker.dtos.RecommendationDTO;
import com.pizzamaker.dtos.RecommendationRequest;
import com.pizzamaker.entities.Recommendation;
import com.pizzamaker.repositories.RecommendationRepository;
import com.pizzamaker.services.RecommendationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class RecommendationServiceImpl implements RecommendationService {
    private final RecommendationRepository recommendationRepository;

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationDTO> getRecommendationsForIngredients(RecommendationRequest request) {
        Set<Long> selected = new HashSet<>();
        if (request.getIngredientIds() != null) {
            selected.addAll(request.getIngredientIds());
        }
        List<Recommendation> all = recommendationRepository.findAll();
        List<RecommendationDTO> result = new ArrayList<>();
        Set<Long> alreadySuggested = new HashSet<>();

        for (Recommendation r : all) {
            if (!"COMPLEMENTARY".equals(r.getType()) || r.getTriggerIngredients() == null || r.getSuggestedIngredient() == null) {
                continue;
            }
            Long suggestedId = r.getSuggestedIngredient().getId();
            // Ne pas recommander un ingrédient déjà sélectionné ni déjà suggéré
            if (selected.contains(suggestedId) || alreadySuggested.contains(suggestedId)) {
                continue;
            }
            List<String> triggers = Arrays.asList(r.getTriggerIngredients().split(","));
            boolean matches = triggers.stream()
                    .map(String::trim)
                    .anyMatch(t -> selected.contains(Long.parseLong(t)));
            if (matches) {
                alreadySuggested.add(suggestedId);
                result.add(RecommendationDTO.builder()
                        .suggestedIngredientId(suggestedId)
                        .suggestedIngredientName(r.getSuggestedIngredient().getName())
                        .suggestedIngredientPrice(r.getSuggestedIngredient().getPrice())
                        .score(r.getScore())
                        .reason("Recommandé en complément de votre sélection")
                        .build());
            }
        }
        return result.stream()
                .sorted(Comparator.comparing(RecommendationDTO::getScore, Comparator.nullsLast(Comparator.reverseOrder())))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<RecommendationDTO> getPopularRecommendations() {
        return recommendationRepository.findByType("POPULAR").stream()
                .filter(r -> r.getSuggestedIngredient() != null)
                .map(r -> RecommendationDTO.builder()
                        .suggestedIngredientId(r.getSuggestedIngredient().getId())
                        .suggestedIngredientName(r.getSuggestedIngredient().getName())
                        .suggestedIngredientPrice(r.getSuggestedIngredient().getPrice())
                        .score(r.getScore())
                        .reason("Populaire")
                        .build())
                .collect(Collectors.toList());
    }
}