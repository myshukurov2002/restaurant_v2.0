package com.company.menu.entity;

import com.company.auth.entity.ProfileEntity;
import com.company.base.BaseEntity;
import com.company.order.entity.OrderEntity;
import com.company.payment.entity.PaymentEntity;
import com.company.table.entity.TableEntity;
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
@Table(name = "menu")
public class MenuEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "owner_id")
    private UUID ownerId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "table_id", insertable = false, updatable = false)
    private TableEntity table;

    @Column(name = "table_id")
    private UUID tableId;

    @Column
    private Boolean isCanceled;

    @OneToMany(mappedBy = "menu", fetch = FetchType.EAGER)
    private List<OrderEntity> orderEntities;

    @OneToMany(mappedBy = "menu", fetch = FetchType.LAZY)
    private List<PaymentEntity> paymentEntities;
}
