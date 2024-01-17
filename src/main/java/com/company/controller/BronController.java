package com.company.controller;

import com.company.model.dto.request.BronCr;
import com.company.model.dto.response.ApiResponse;
import com.company.model.dto.response.BronResp;
import com.company.service.BronService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/bron")
@RequiredArgsConstructor

public class BronController {

    private final BronService bronService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<BronResp>> bron(
            @RequestParam UUID tableId,
            @Valid @RequestBody BronCr bronCr) {
        return ResponseEntity
                .ok(bronService.bron(tableId, bronCr));
    }
}
