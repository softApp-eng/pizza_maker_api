package com.pizzamaker.entities;

import com.pizzamaker.entities.enums.CrustType;
import com.pizzamaker.entities.enums.PizzaSize;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CustomPizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String name;

    @Enumerated(EnumType.STRING)
    private PizzaSize size;

    @Enumerated(EnumType.STRING)
    private CrustType crust;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal totalPrice;

    private String userId;

    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @OneToMany(mappedBy = "customPizza", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Builder.Default
    private List<CustomPizzaIngredient> ingredients = new ArrayList<>();
}