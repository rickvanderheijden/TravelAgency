package com.travelagency.repository;

import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findByUsername(String Username);
    List<User> findByTravelGroups(TravelGroup travelGroup);
}
