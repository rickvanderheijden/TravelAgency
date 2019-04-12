package com.travelagency.repository;

import com.travelagency.model.TripService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripServiceRepository extends JpaRepository<TripService, Long> {
}
