package com.travelagency.rest;

import com.travelagency.controllers.BookingController;
import com.travelagency.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bookings")
public class BookingResource {

    private final BookingController bookingController;

    public BookingResource(BookingController bookingController) {
        this.bookingController = bookingController;
    }

    @PostMapping()
    public Optional<Booking> createBooking(@Valid @RequestBody Booking booking) {
        return bookingController.createBooking(booking);
    }

    @PutMapping()
    public ResponseEntity<Booking> updateBooking(@Valid @RequestBody Booking booking) {
        Booking updatedBooking = this.bookingController.updateBooking(booking);
        if(updatedBooking == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updatedBooking);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Booking> deleteBooking(@PathVariable Long id) {
        boolean isDeleted = this.bookingController.deleteBooking(id);
        if(!isDeleted){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/{id}")
    public Optional<Booking> getById(@PathVariable final Long id) {
        return bookingController.getById(id);
    }

    @GetMapping(value = "/token")
    public Optional<List<Booking>> getBookingsByToken(HttpServletRequest request) {
        return bookingController.getBookingsByToken(request);
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Optional<List<Booking>> getAll() {
        return bookingController.getAllBookings();
    }
}
