package com.travelagency.repository;

import com.travelagency.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {
    List<Payment> findByUserUsername(String Username);
    List<Payment> findAllByBooking_Id(Long bookingId);
    Payment findFirstByUserUsername(String Username);
}
