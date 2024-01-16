package com.company.entity;

import com.company.model.enums.ProfileRole;
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
