package com.company.bron.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.UUID;

@Builder
public record BronResp(
        UUID id,
        Integer tableNumber,
        LocalDateTime bronDate
) {
}
