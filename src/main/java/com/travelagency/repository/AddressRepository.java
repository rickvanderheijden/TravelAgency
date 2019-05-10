package com.travelagency.repository;

import com.travelagency.model.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address findByAddressLineAndCityName(String addressLine, String cityName);
}
