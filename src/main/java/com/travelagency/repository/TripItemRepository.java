package com.travelagency.repository;

import com.travelagency.model.TripItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TripItemRepository extends JpaRepository<TripItem, Long> {
    TripItem getByAddressCityName(String cityName);
}
