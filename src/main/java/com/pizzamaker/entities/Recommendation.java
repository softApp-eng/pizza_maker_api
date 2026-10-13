package com.pizzamaker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Recommendation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String type; // COMPLEMENTARY, POPULAR

    private String triggerIngredients; // comma separated ids

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggested_ingredient_id")
    private Ingredient suggestedIngredient;

    private Double score;
}