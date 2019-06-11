package com.travelagency.repository;

import com.travelagency.model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HotelRepository extends JpaRepository<Hotel, Long> {
    List<Hotel> getByAddressCityName(String cityName);
    Hotel getFirstByAddressCityName(String cityName);
    Hotel getFirstByName(String name);
}
