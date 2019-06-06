package com.travelagency.repository;

import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelGroupRepository extends JpaRepository<TravelGroup, Long> {
    TravelGroup findByName(String username);
    List<TravelGroup> findByUser(User user);
}
