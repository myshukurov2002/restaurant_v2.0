package com.company.repository;

import com.company.entity.TableEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface TableRepository extends JpaRepository<TableEntity, UUID> {
    Optional<TableEntity> findByNumberAndVisibilityTrue(Integer number);

    Optional<TableEntity> findByIdAndVisibilityTrue(UUID id);

    List<TableEntity> findAllByVisibilityTrue(Pageable pageable);

    List<TableEntity> findAllByIsBusyAndVisibilityTrue(Boolean isBusy, Pageable pageable);

    Optional<TableEntity> findByIdAndIsBusyFalseAndVisibilityTrue(UUID id);

    Optional<TableEntity> findByNumberAndIsBusyFalseAndVisibilityTrue(Integer s);
}
