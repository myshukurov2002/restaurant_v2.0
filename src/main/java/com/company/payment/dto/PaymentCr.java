package com.company.payment.dto;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

public record PaymentCr(
        @NotBlank(message = "{menu.id.not.blank}")
        UUID menuId
) {
}
