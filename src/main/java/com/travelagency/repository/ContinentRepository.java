package com.travelagency.repository;

import com.travelagency.model.Continent;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContinentRepository extends JpaRepository<Continent, Long> {
    Continent findByName(String name);
}
