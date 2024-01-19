package com.company.payment;

import com.company.base.ApiResponse;
import com.company.payment.dto.PaymentCr;
import com.company.payment.dto.PaymentResp;
import com.company.payment.entity.PaymentEntity;
import com.company.payment.mapper.PaymentDtoMapper;
import com.company.payment.mapper.PaymentEntityMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    private final PaymentDtoMapper paymentDtoMapper;
    private final PaymentEntityMapper paymentEntityMapper;

    @Override
    @Transactional
    public ApiResponse<PaymentResp> create(PaymentCr paymentCr) {

        PaymentEntity payment = paymentEntityMapper.apply(paymentCr);

        PaymentEntity savedPayment = paymentRepository.save(payment);

        return new ApiResponse<>(true, paymentDtoMapper.apply(savedPayment));
    }
}
