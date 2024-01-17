package com.company.model.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

public record MenuCr(
        @NotBlank(message = "{table.number.not.blank}")
        Integer tableNumber,
        @NotNull(message = "{food.list.not.null}")
        List<String> foodNames) {
}
