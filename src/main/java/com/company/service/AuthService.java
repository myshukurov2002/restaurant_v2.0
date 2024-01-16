package com.company.service;

import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.request.AuthDto;
import com.company.model.dto.request.RegistrationDto;
import com.company.model.dto.response.JwtResponse;

public interface AuthService {
    ApiResponse<JwtResponse> login(AuthDto auth);

    ApiResponse<JwtResponse> registration(RegistrationDto reg);
}
