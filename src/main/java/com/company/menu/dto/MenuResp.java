package com.company.menu.dto;

import com.company.food.dto.FoodResp;
import com.company.order.dto.OrderResp;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Builder
public record MenuResp(
        UUID id,
        List<OrderResp> orders,
        Integer tableNumber
) {
}
