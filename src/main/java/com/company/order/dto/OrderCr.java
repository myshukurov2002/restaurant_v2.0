package com.company.order.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderCr(

        UUID foodId,
        BigDecimal price,
        UUID menuId
) {
}
