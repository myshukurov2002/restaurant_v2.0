package com.company.repository;

import com.company.entity.FoodEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface FoodRepository extends JpaRepository<FoodEntity, UUID> {

    Optional<FoodEntity> findByNameAndVisibilityTrue(String name);

    Optional<FoodEntity> findByIdAndVisibilityTrue(UUID id);

    List<FoodEntity> findAllByVisibilityTrue(Pageable pageable);
}
