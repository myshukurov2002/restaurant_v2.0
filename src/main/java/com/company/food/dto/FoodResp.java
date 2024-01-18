package com.company.food.dto;

import lombok.Builder;

import java.util.UUID;
@Builder
public record FoodResp(
        UUID id,
        String name,
        Double price
) {
}
