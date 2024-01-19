package com.company.payment.entity;

import com.company.auth.entity.ProfileEntity;
import com.company.base.BaseEntity;
import com.company.menu.entity.MenuEntity;
import com.company.table.entity.TableEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class PaymentEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private ProfileEntity profile;

    @Column(name = "user_id")
    private UUID userId;

    @Column
    private BigDecimal sum = BigDecimal.ZERO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "menu_id", insertable = false, updatable = false)
    private MenuEntity menu;

    @Column(name = "menu_id")
    private UUID menuId;

    @Column
    private Boolean isPayed;

}
