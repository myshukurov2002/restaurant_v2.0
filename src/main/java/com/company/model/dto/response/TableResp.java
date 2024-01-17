package com.company.model.dto.response;

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
