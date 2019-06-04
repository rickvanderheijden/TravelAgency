package com.travelagency.rest;

import com.travelagency.controllers.BookingController;
import com.travelagency.model.Booking;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
}
