package com.company.auth.entity;

import com.company.base.BaseEntity;
import com.company.auth.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "profile")
public class ProfileEntity extends BaseEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String phone;

    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    private ProfileStatus status;

    @OneToMany(mappedBy = "profileEntity", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ProfileRoleEntity> profileRoleList;
}
