package com.company.food.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public record FoodCr(
        @NotBlank(message = "{valid.food.name}")
        String name,

        @NotBlank(message = "{valid.food.price}")
        BigDecimal price
) {
}
