package com.company.model.dto.request;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Builder
public record BronCr(
        @NotBlank(message = "{tableNumber.not.blank}")
        Integer tableNumber,
        @NotNull(message = "{bronDate.not.null}")
        LocalDateTime bronDate
) {
}
