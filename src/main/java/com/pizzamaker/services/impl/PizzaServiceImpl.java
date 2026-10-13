package com.pizzamaker.services.impl;

import com.pizzamaker.dtos.IngredientDTO;
import com.pizzamaker.dtos.PizzaDTO;
import com.pizzamaker.entities.Pizza;
import com.pizzamaker.exceptions.ResourceNotFoundException;
import com.pizzamaker.repositories.PizzaRepository;
import com.pizzamaker.services.PizzaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PizzaServiceImpl implements PizzaService {
    private final PizzaRepository pizzaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<PizzaDTO> getAllActivePizzas() {
        return pizzaRepository.findByIsActiveTrue().stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public PizzaDTO getPizzaById(Long id) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found with id: " + id));
        return mapToDTO(pizza);
    }

    private PizzaDTO mapToDTO(Pizza pizza) {
        List<IngredientDTO> ingredientDTOs = pizza.getIngredients().stream()
                .map(pi -> IngredientDTO.builder()
                        .id(pi.getIngredient().getId())
                        .name(pi.getIngredient().getName())
                        .category(pi.getIngredient().getCategory())
                        .price(pi.getIngredient().getPrice())
                        .isAvailable(pi.getIngredient().getIsAvailable())
                        .maxQuantity(pi.getIngredient().getMaxQuantity())
                        .build())
                .collect(Collectors.toList());
        return PizzaDTO.builder()
                .id(pizza.getId())
                .name(pizza.getName())
                .description(pizza.getDescription())
                .imageUrl(pizza.getImageUrl())
                .basePrice(pizza.getBasePrice())
                .isCustom(pizza.getIsCustom())
                .ingredients(ingredientDTOs)
                .build();
    }
}