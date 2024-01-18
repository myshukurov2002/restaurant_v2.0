package com.company.food.entity;

import com.company.auth.entity.ProfileEntity;
import com.company.base.BaseEntity;
import com.company.order.entity.OrderEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "food")
public class FoodEntity extends BaseEntity {

    @Column
    private String name;

    @Column
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;

    @Column(name = "owner_id")
    private UUID ownerId;

    @OneToMany(mappedBy = "food", fetch = FetchType.LAZY)
    private List<OrderEntity> order;

}
