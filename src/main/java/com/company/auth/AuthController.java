package com.company.auth;

import com.company.auth.dto.AuthDto;
import com.company.auth.dto.RegistrationDto;
import com.company.base.ApiResponse;
import com.company.auth.dto.JwtResponse;
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
