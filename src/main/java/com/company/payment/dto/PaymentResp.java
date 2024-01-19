package com.company.payment.dto;

import com.company.menu.dto.MenuResp;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record PaymentResp(
        UUID id,
        UUID userId,
        MenuResp menuResp,
        BigDecimal sum,
        Boolean isPayed
) {
}
