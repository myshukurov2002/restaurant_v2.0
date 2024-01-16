package com.company.model.dto.request;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Builder
public record TableCr(

        @NotBlank(message = "{table.number}")
        @Min(message = "{table.number.min}", value = 1)
        Integer number,

        @NotBlank(message = "{table.type}")
        @Min(message = "{table.type.min}", value = 1)
        Integer type
) {
}
