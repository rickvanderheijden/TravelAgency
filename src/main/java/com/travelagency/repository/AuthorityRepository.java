package com.travelagency.repository;

import com.travelagency.model.Authority;
import com.travelagency.model.AuthorityName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Authority findByName(AuthorityName roleUser);
}
