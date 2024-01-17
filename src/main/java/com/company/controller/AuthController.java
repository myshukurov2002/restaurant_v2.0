package com.company.controller;

import com.company.model.dto.request.AuthDto;
import com.company.model.dto.request.RegistrationDto;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.JwtResponse;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(
            @Valid @RequestBody AuthDto auth) {
        return ResponseEntity
                .ok(authService.login(auth));
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<JwtResponse>> registration(
            @Valid @RequestBody RegistrationDto reg) {
        return ResponseEntity
                .ok(authService.registration(reg));
    }
}
