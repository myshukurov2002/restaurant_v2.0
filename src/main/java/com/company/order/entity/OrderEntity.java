package com.company.order.entity;

import com.company.base.BaseEntity;
import com.company.food.entity.FoodEntity;
import com.company.order.enums.FoodStatus;
import com.company.menu.entity.MenuEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "orders")
public class OrderEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "food_id", updatable = false, insertable = false)
    private FoodEntity food;

    @Column(name = "food_id")
    private UUID foodId;

    @Column
    @Enumerated(EnumType.STRING)
    private FoodStatus foodStatus;

    @Column
    private Integer quantity = 1; // by default

    @Column
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private MenuEntity menu;

    @Column(name = "menu_id")
    private UUID menuId;
}
