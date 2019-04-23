package com.travelagency.repository;

import com.travelagency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findByNameContains(String name);
}