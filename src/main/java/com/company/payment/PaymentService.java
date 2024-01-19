package com.company.payment;

import com.company.base.ApiResponse;
import com.company.payment.dto.PaymentCr;
import com.company.payment.dto.PaymentResp;

public interface PaymentService {
    ApiResponse<PaymentResp> create(PaymentCr paymentCr);
}
