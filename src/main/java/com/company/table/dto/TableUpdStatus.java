package com.company.table.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;

public record TableUpdStatus(
        @NotBlank(message = "{status.not.blank}")
        Boolean isBusy
) {
}
