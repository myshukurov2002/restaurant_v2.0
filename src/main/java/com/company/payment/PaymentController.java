package com.company.payment;

import com.company.base.ApiResponse;
import com.company.payment.dto.PaymentCr;
import com.company.payment.dto.PaymentResp;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1/payment")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ApiResponse<PaymentResp>> create(@Valid @RequestBody PaymentCr paymentCr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(paymentService.create(paymentCr));
    }
}
