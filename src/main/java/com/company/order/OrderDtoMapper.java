package com.company.order;

import com.company.order.dto.OrderResp;
import com.company.order.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;

@Service
public class OrderDtoMapper implements Function<OrderEntity, OrderResp> {
    @Override
    public OrderResp apply(OrderEntity orderEntity) {

        return OrderResp.builder()
                .id(orderEntity.getId())
                .price(orderEntity.getPrice())
                .foodId(orderEntity.getFoodId())
                .menuId(orderEntity.getMenuId())
                .build();
    }
}
