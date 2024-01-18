package com.company.auth;

import com.company.base.ApiResponse;
import com.company.auth.dto.AuthDto;
import com.company.auth.dto.RegistrationDto;
import com.company.auth.dto.JwtResponse;

public interface AuthService {
    ApiResponse<JwtResponse> login(AuthDto auth);

    ApiResponse<JwtResponse> registration(RegistrationDto reg);
}
