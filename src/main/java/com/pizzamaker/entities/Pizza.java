package com.pizzamaker.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(length = 500)
    private String description;

    private String imageUrl;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal basePrice;

    private Boolean isCustom = false;

    private Boolean isActive = true;

    @OneToMany(mappedBy = "pizza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<PizzaIngredient> ingredients = new ArrayList<>();
}