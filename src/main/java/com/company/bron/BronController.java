package com.company.bron;

import com.company.bron.dto.BronCr;
import com.company.base.ApiResponse;
import com.company.bron.dto.BronResp;
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
