package com.company.model.dto.response;

import com.company.model.enums.ProfileRole;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.util.List;
import java.util.UUID;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record JwtResponse(
        UUID id,
        List<ProfileRole> roles,
        String jwt) {

    public JwtResponse(UUID id, List<ProfileRole> roles) {
        this(id, roles, null);
    }
}
