package com.company.model.dto;

import lombok.Builder;

import java.util.UUID;

@Builder
public record TableResp(
        UUID id,
        Integer number,
        Integer type,
        Boolean isBusy
) {
}
