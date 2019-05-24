package com.travelagency.repository;

import com.travelagency.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    Hotel getByAddressCityName(String cityName);
}
