package com.pizzamaker.services.impl;

import com.pizzamaker.dtos.CustomPizzaIngredientRequest;
import com.pizzamaker.dtos.CustomPizzaRequest;
import com.pizzamaker.dtos.CustomPizzaResponse;
import com.pizzamaker.entities.CustomPizza;
import com.pizzamaker.entities.CustomPizzaIngredient;
import com.pizzamaker.entities.Ingredient;
import com.pizzamaker.exceptions.ResourceNotFoundException;
import com.pizzamaker.mappers.DtoMapper;
import com.pizzamaker.repositories.CustomPizzaRepository;
import com.pizzamaker.repositories.IngredientRepository;
import com.pizzamaker.services.CustomPizzaService;
import com.pizzamaker.utils.PriceCalculator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CustomPizzaServiceImpl implements CustomPizzaService {
    private final CustomPizzaRepository customPizzaRepository;
    private final IngredientRepository ingredientRepository;
    private final PriceCalculator priceCalculator;
    private final DtoMapper dtoMapper;

    @Override
    public CustomPizzaResponse createCustomPizza(CustomPizzaRequest request) {
        CustomPizza customPizza = CustomPizza.builder()
                .name(request.getName() != null && !request.getName().isBlank() ? request.getName() : "Ma pizza personnalisée")
                .size(request.getSize())
                .crust(request.getCrust())
                .build();

        List<CustomPizzaIngredient> ingredientsList = new ArrayList<>();
        BigDecimal total = priceCalculator.calculateBaseForSizeAndCrust(request.getSize(), request.getCrust());

        if (request.getIngredients() != null) {
            for (CustomPizzaIngredientRequest ir : request.getIngredients()) {
                if (ir.getIngredientId() != null && ir.getQuantity() != null && ir.getQuantity() > 0) {
                    Ingredient ingredient = ingredientRepository.findById(ir.getIngredientId())
                            .orElseThrow(() -> new ResourceNotFoundException("Ingredient not found: " + ir.getIngredientId()));
                    CustomPizzaIngredient cpi = CustomPizzaIngredient.builder()
                            .customPizza(customPizza)
                            .ingredient(ingredient)
                            .quantity(ir.getQuantity())
                            .build();
                    ingredientsList.add(cpi);
                    total = total.add(ingredient.getPrice().multiply(new BigDecimal(ir.getQuantity())));
                }
            }
        }
        customPizza.setIngredients(ingredientsList);
        customPizza.setTotalPrice(total);
        CustomPizza saved = customPizzaRepository.save(customPizza);
        return dtoMapper.toCustomPizzaResponse(saved);
    }

    @Override
    @Transactional(readOnly = true)
    public CustomPizzaResponse getCustomPizzaById(Long id) {
        CustomPizza customPizza = customPizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Custom pizza not found: " + id));
        return dtoMapper.toCustomPizzaResponse(customPizza);
    }
}