package com.company.order;

import com.company.order.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.UUID;

public interface OrderRepository extends JpaRepository<OrderEntity, UUID> {
    @Query(value = "select coalesce(sum(o.price), 0)" +
                   " from orders o" +
                   " where o.menu_id = :menuId",
            nativeQuery = true)
    BigDecimal getSumByMenuId(@Param("menuId") UUID menuId);
}
