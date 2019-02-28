package com.travelagency.repository;

import com.travelagency.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findByUsername(String Username);
}
