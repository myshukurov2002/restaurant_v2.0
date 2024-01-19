package com.company.food.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;
@Builder
public record FoodResp(
        UUID id,
        String name,
        BigDecimal price
) {
}
