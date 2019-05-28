package com.travelagency.repository;

import com.travelagency.model.TripItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TripItemRepository extends JpaRepository<TripItem, Long> {
    List<TripItem> getByAddressCityName(String cityName);
}
