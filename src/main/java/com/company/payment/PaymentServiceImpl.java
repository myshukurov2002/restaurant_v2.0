package com.company.payment;

import com.company.base.ApiResponse;
import com.company.payment.dto.PaymentCr;
import com.company.payment.dto.PaymentResp;
import com.company.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService{

    private final PaymentRepository paymentRepository;

    @Override
    public ApiResponse<PaymentResp> create(PaymentCr paymentCr) {

        PaymentEntity payment = toEntity(paymentCr);
        return null;
    }

    private PaymentEntity toEntity(PaymentCr paymentCr) {

        return null;
    }
}
