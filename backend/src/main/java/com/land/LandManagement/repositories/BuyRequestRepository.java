package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.BuyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BuyRequestRepository extends JpaRepository<BuyRequest, Integer> {
    List<BuyRequest> findByLandIdAndStatus(Integer id, String pending);

    @Modifying
    @Transactional
    @Query("DELETE FROM BuyRequest b WHERE b.land.id = :landId")
    void deleteByLandId(@Param("landId") Integer landId);
}
