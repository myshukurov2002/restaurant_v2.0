package com.company.payment.mapper;

import com.company.configs.security.utils.SecurityUtil;
import com.company.menu.MenuService;
import com.company.menu.dto.MenuResp;
import com.company.order.OrderService;
import com.company.payment.dto.PaymentCr;
import com.company.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PaymentEntityMapper implements Function<PaymentCr, PaymentEntity> {

    private final OrderService orderService;

    @Override
    public PaymentEntity apply(PaymentCr paymentCr) {

        return PaymentEntity.builder()
                .menuId(paymentCr.menuId())
                .sum(orderService.getSumByMenuId(paymentCr.menuId()).data())
                .isPayed(Boolean.TRUE)
                .userId(SecurityUtil.getCurrentProfileId())
                .build();
    }
}
