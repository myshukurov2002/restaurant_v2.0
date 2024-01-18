package com.company.bron;

import com.company.bron.BronEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

public interface BronRepository extends JpaRepository<BronEntity, UUID> {
    @Query("select b" +
           " from BronEntity b" +
           " where b.tableId = :tableId" +
           " and b.status = true" +
           " and function('TIMESTAMPDIFF', second , :localDateTime, b.bronDate) <= 3600")
    Optional<BronEntity> findBronWithinOneHour(@Param("tableId") UUID tableId, @Param("localDateTime") LocalDateTime localDateTime);
}
