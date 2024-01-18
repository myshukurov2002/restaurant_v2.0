package com.company.auth.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
@Builder
public record AuthDto(
        @NotBlank(message = "{valid.phone}") String phone,
        @NotBlank(message = "{valid.passwd}") @Size(min = 6, message = "{valid.passwd.min}") String password
) {}

