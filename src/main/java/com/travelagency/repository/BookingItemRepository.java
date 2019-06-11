package com.travelagency.repository;

import com.travelagency.model.BookingItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingItemRepository extends JpaRepository<BookingItem, Long> {
}
