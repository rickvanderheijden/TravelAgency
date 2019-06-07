package com.travelagency.repository;

import com.travelagency.model.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface TripRepository extends JpaRepository<Trip, Long> {
    List<Trip> findDistinctByNameContainsOrSummaryContainsOrDescriptionContains(String name, String summary, String description);
    List<Trip> findDistinctByDestinations_City_Country_Continent_Name(String continentName);
    List<Trip> findDistinctByDestinations_City_Country_Name(String countryName);
}
