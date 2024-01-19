package com.company.payment.mapper;

import com.company.configs.security.utils.SecurityUtil;
import com.company.menu.MenuService;
import com.company.menu.dto.MenuResp;
import com.company.order.OrderService;
import com.company.payment.dto.PaymentResp;
import com.company.payment.entity.PaymentEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class PaymentDtoMapper implements Function<PaymentEntity, PaymentResp> {

    private final OrderService orderService;
    private final MenuService menuService;

    @Override
    public PaymentResp apply(PaymentEntity paymentEntity) {

        MenuResp menuResp = menuService
                .getById(paymentEntity.getMenuId()).data();

        BigDecimal sum = orderService
                .getSumByMenuId(paymentEntity.getMenuId()).data();

        return PaymentResp.builder()
                .sum(sum)
                .userId(SecurityUtil.getCurrentProfileId())
                .menuResp(menuResp)
                .build();
    }
}
