package com.company.check;

import com.company.auth.entity.ProfileEntity;
import com.company.base.BaseEntity;
import com.company.order.entity.OrderEntity;
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
@Table(name = "checks")
public class CheckEntity extends BaseEntity {

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
    private Double sum = 0.0;

    @OneToMany(mappedBy = "check", fetch = FetchType.EAGER)
    private List<OrderEntity> orderEntities;

}
