package com.land.LandManagement.repositories;

import com.land.LandManagement.domain.tables.Land;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LandRepository extends JpaRepository<Land, Integer> {
    Optional<Land> findById(Integer id);
}
