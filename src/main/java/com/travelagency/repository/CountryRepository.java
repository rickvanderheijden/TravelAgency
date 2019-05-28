package com.travelagency.repository;

import com.travelagency.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
    @Query("SELECT CASE WHEN COUNT(c) > 0 THEN 'true' ELSE 'false' END FROM Country c WHERE c.name = ?1")
    boolean existsByName(String name);
    List<Country> findByContinent_Name(String continentName);
}
