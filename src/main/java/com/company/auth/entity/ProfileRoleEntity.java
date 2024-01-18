package com.company.auth.entity;

import com.company.base.BaseEntity;
import com.company.auth.enums.ProfileRole;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile_role")
public class ProfileRoleEntity extends BaseEntity {

    @Column
    @Enumerated(EnumType.STRING)
    private ProfileRole profileRole;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", insertable = false, updatable = false)
    private ProfileEntity profileEntity;

    @Column(name = "profile_id")
    private UUID profileId;
}
