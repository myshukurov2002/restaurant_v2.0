package com.company.order;

import com.company.base.ApiResponse;
import com.company.order.dto.OrderCr;
import com.company.order.dto.OrderResp;
import io.micrometer.core.instrument.binder.db.MetricsDSLContext;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderService {
    ApiResponse<OrderResp> create(OrderCr orderCr);

    ApiResponse<BigDecimal> getSumByMenuId(UUID id);
}
