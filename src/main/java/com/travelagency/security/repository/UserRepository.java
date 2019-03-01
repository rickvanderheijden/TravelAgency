package com.travelagency.security.repository;

import com.travelagency.model.security.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmailAddress(String emailAddress);
    User findByUsername(String Username);
}
