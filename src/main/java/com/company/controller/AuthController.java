package com.company.controller;

import com.company.config.security.details.SecurityUtil;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.request.AuthDto;
import com.company.model.dto.request.RegistrationDto;
import com.company.model.dto.response.JwtResponse;
import com.company.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JwtResponse>> login(@Valid @RequestBody AuthDto auth) {
        return ResponseEntity.ok(authService.login(auth));
    }

    @PostMapping("/registration")
    public ResponseEntity<ApiResponse<JwtResponse>> registration(@Valid @RequestBody RegistrationDto reg) {
        return ResponseEntity.ok(authService.registration(reg));
    }
}
