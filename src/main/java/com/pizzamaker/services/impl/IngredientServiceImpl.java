package com.pizzamaker.services.impl;

import com.pizzamaker.dtos.IngredientDTO;
import com.pizzamaker.entities.Ingredient;
import com.pizzamaker.entities.enums.IngredientCategory;
import com.pizzamaker.repositories.IngredientRepository;
import com.pizzamaker.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class IngredientServiceImpl implements IngredientService {
    private final IngredientRepository ingredientRepository;

    @Override
    @Transactional(readOnly = true)
    public List<IngredientDTO> getAllAvailableIngredients() {
        return ingredientRepository.findByIsAvailableTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<IngredientDTO> getIngredientsByCategory(IngredientCategory category) {
        return ingredientRepository.findByCategoryAndIsAvailableTrue(category).stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    private IngredientDTO mapToDTO(Ingredient ingredient) {
        return IngredientDTO.builder()
                .id(ingredient.getId())
                .name(ingredient.getName())
                .category(ingredient.getCategory())
                .price(ingredient.getPrice())
                .isAvailable(ingredient.getIsAvailable())
                .maxQuantity(ingredient.getMaxQuantity())
                .imageUrl(ingredient.getImageUrl())
                .build();
    }
}