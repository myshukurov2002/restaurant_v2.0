package com.company.food.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

public record FoodUpd(
        @NotBlank(message = "{valid.food.name}")
        String name,

        @NotBlank(message = "{valid.food.price}")
        BigDecimal price
) {

}
