package com.travelagency.repository;

import com.travelagency.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DestinationRepository extends JpaRepository<Destination, Long> {
    Destination getByCityName(String cityName);
}
