package com.company.order.dto;

import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderResp(
        UUID id,
        BigDecimal price,
        UUID foodId,
        UUID menuId
) {
}
