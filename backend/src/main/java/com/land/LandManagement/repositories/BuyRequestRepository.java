package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.BuyRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuyRequestRepository extends JpaRepository<BuyRequest, Integer> {
}
