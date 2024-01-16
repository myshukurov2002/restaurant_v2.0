package com.company.repository;

import com.company.entity.TableEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, UUID> {
    Optional<TableEntity> findByNumberAndVisibilityTrue(Integer number);

    Optional<TableEntity> findByIdAndVisibilityTrue(UUID id);
}
