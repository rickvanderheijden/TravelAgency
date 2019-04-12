package com.travelagency.repository;

import com.travelagency.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CountryRepository extends JpaRepository<Country, Long> {
    Country findByName(String name);
}
