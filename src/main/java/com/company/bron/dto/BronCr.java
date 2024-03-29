package com.company.bron.dto;

import lombok.Builder;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public record BronCr(
        @NotBlank(message = "{tableNumber.not.blank}")
        Integer tableNumber,
        @NotNull(message = "{bronDate.not.null}")
        LocalDateTime bronDate
) {
}
