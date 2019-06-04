package com.travelagency.controllers;

import com.travelagency.model.Address;
import com.travelagency.model.BookingItem;
import com.travelagency.model.City;
import com.travelagency.model.TripItem;
import com.travelagency.repository.BookingItemRepository;
import com.travelagency.repository.TripItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookingItemController {

    private final BookingItemRepository bookingItemRepository;

    public BookingItemController(BookingItemRepository bookingItemRepository) {
        this.bookingItemRepository = bookingItemRepository;
    }

    public Optional<BookingItem> createBookingItem(BookingItem bookingItem) {
        if (bookingItem == null) return Optional.empty();

        return Optional.of(bookingItemRepository.save(bookingItem));
    }
}
