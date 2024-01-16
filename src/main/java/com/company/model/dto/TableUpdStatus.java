package com.company.model.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

@Builder
public record TableUpdStatus(
        @NotBlank(message = "{status.not.blank}")
        Boolean isBusy
) {
}
