package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.Land;
import com.land.LandManagement.domain.tables.OwnershipHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OwnershipHistoryRepository extends JpaRepository<OwnershipHistory, Integer> {
    Optional<OwnershipHistory> findById(Integer id);

    //@Query("SELECT o FROM OwnershipHistory o WHERE o.land.id = :landId ORDER BY o.orderNumber")
    List<OwnershipHistory> findByLandIdOrderByOrderNumber(Integer landId);

    @Query("SELECT MAX(o.orderNumber) FROM OwnershipHistory o WHERE o.land.id = :landId")
    Optional<Integer> findMaxOrderNumberByLandId(@Param("landId") Integer landId);
}
