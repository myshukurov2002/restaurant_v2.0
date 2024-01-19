package com.company.order;

import com.company.base.ApiResponse;
import com.company.configs.i18n.MessageService;
import com.company.expection.exp.AppBadRequestException;
import com.company.order.dto.OrderCr;
import com.company.order.dto.OrderResp;
import com.company.order.entity.OrderEntity;
import com.company.order.enums.FoodStatus;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderDtoMapper orderDtoMapper;
    private final MessageService messageService;

    @Override
    @Transactional
    public ApiResponse<OrderResp> create(OrderCr orderCr) {

        OrderEntity order = toEntity(orderCr);
        order.setFoodStatus(FoodStatus.PREPARING);

        OrderEntity savedOrder = orderRepository.save(order);

        log.info("[{}] order created", (savedOrder.getId()));

        return new ApiResponse<>(true, orderDtoMapper.apply(savedOrder));
    }

    @Override
    @Transactional
    public ApiResponse<BigDecimal> getSumByMenuId(UUID id) {

        BigDecimal sum = orderRepository.getSumByMenuId(id);

        return sum.compareTo(BigDecimal.ZERO) == 0
                ? new ApiResponse<>(false, messageService.getMessage("sum.is.0"))
                : new ApiResponse<>(true, sum);
    }


    private OrderEntity toEntity(OrderCr orderCr) {

        return OrderEntity.builder()
                .price(orderCr.price())
                .foodId(orderCr.foodId())
                .menuId(orderCr.menuId())
                .build();
    }
}
