package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OwnershipHistoryRepository extends JpaRepository<OwnershipHistory, Integer> {
    Optional<OwnershipHistory> findById(Integer id);
}
