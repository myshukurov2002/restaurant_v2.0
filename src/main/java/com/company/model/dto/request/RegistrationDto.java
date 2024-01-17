package com.company.model.dto.request;

import lombok.Builder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDate;

public record RegistrationDto(
        @NotBlank(message = "{validation.firstname.notblank}") String firstName,
        @NotBlank(message = "{validation.lastname.notblank}") String lastName,
        @Pattern(regexp = "\\+998[1-9][0-9]{9}", message = "{validation.phone.invalidformat}") String phone,
        @NotBlank(message = "{validation.password}")
        @Min(message = "{validation.password.min}", value = 6)
        String password
) {
}
