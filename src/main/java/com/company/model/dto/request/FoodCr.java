package com.company.model.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record FoodCr(
        @NotBlank(message = "{valid.food.name}")
        String name,

        @NotBlank(message = "{valid.food.price}")
        Double price
) {
}
