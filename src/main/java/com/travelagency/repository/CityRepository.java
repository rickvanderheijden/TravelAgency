package com.travelagency.repository;

import com.travelagency.model.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String name);
    List<City> findByCountry_Name(String countryName);

    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM City c WHERE c.name = ?1")
    boolean existsByName(String name);
}
