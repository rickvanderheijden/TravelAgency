package com.travelagency.security.repository;

import com.travelagency.model.security.Authority;
import com.travelagency.model.security.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName roleUser);
}
