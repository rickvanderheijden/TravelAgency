package com.travelagency.repository;

import com.travelagency.model.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.*;

public interface IServiceRepository extends JpaRepository<Service, Long> {

}
