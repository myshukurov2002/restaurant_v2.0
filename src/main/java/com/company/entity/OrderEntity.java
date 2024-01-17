package com.company.entity;

import jakarta.persistence.*;
import lombok.*;

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
    private Integer quantity = 1; // by default

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "check_id", insertable = false, updatable = false)
    private CheckEntity check;

    @Column(name = "check_id")
    private UUID checkId;
}
