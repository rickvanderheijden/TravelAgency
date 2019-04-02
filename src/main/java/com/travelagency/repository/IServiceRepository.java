package com.travelagency.repository;

import com.travelagency.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IServiceRepository extends JpaRepository<Service, Long> {

}
