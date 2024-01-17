package com.company.model.dto.request;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public record TableCr(

        @NotBlank(message = "{table.tableNumber}")
        @Min(message = "{table.tableNumber.min}", value = 1)
        Integer number,

        @NotBlank(message = "{table.type}")
        @Min(message = "{table.type.min}", value = 1)
        Integer type
) {
}
