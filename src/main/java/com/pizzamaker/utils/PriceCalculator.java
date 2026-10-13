package com.pizzamaker.utils;

import com.pizzamaker.entities.enums.CrustType;
import com.pizzamaker.entities.enums.PizzaSize;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class PriceCalculator {
    public BigDecimal calculateBaseForSizeAndCrust(PizzaSize size, CrustType crust) {
        BigDecimal base = BigDecimal.ZERO;
        if (size != null) {
            switch (size) {
                case SMALL -> base = base.add(new BigDecimal("5.00"));
                case MEDIUM -> base = base.add(new BigDecimal("7.00"));
                case LARGE -> base = base.add(new BigDecimal("9.00"));
                default -> base = base.add(new BigDecimal("7.00"));
            }
        } else {
            base = base.add(new BigDecimal("7.00"));
        }
        if (crust != null) {
            switch (crust) {
                case THIN -> base = base.add(new BigDecimal("0.00"));
                case CLASSIC -> base = base.add(new BigDecimal("0.00"));
                case THICK -> base = base.add(new BigDecimal("1.00"));
                case STUFFED -> base = base.add(new BigDecimal("2.00"));
                default -> {}
            }
        }
        return base;
    }
}