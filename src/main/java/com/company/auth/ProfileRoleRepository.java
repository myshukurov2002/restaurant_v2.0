package com.company.auth;

import com.company.auth.entity.ProfileRoleEntity;
import com.company.auth.enums.ProfileRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.UUID;

public interface ProfileRoleRepository extends JpaRepository<ProfileRoleEntity, UUID> {
    List<ProfileRoleEntity> findAllByProfileId(UUID id);

    @Query(value = " select p.profileRole from ProfileRoleEntity as p where p.profileId =:profileId")
    List<ProfileRole> findAllRoleList(@Param("profileId") UUID profileId);
}
