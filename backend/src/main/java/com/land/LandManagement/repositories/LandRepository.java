package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.Land;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LandRepository extends JpaRepository<Land, Integer> {
}
