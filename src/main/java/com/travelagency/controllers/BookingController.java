package com.travelagency.controllers;

import com.travelagency.model.*;
import com.travelagency.repository.BookingRepository;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Service
public class BookingController {

    private final BookingRepository bookingRepository;
    private final BookingItemController bookingItemController;
    private final GeographyController geographyController;
    private final UserController userController;
    private final JwtController jwtController;

    public BookingController(
            BookingRepository bookingRepository,
            BookingItemController bookingItemController,
            GeographyController geographyController,
            UserController userController, JwtController jwtController){
        this.bookingRepository = bookingRepository;
        this.bookingItemController = bookingItemController;
        this.geographyController = geographyController;
        this.userController = userController;
        this.jwtController = jwtController;
    }

    public Optional<Booking> createBooking(Booking booking) {
        if(booking == null) return Optional.empty();

        List<BookingItem> bookingItems = new ArrayList<>();
        if (booking.getBookingItems() != null) {
            for (BookingItem bookingItem : booking.getBookingItems()) {
                Optional<BookingItem> item = bookingItemController.createBookingItem(bookingItem);
                if (item.isPresent()) {
                    bookingItems.add(item.get());
                }
            }
        }
        booking.setBookingItems(bookingItems);

        if (booking.getBooker() != null) {
            Optional<User> booker = userController.getUserById(booking.getBooker().getId());
            if (booker.isPresent()) {
                booking.setBooker(booker.get());
            }
        }

        if (booking.getAddress() != null) {
            Optional<Address> address = geographyController.getAddress(booking.getAddress());
            if (address.isPresent()) {
                booking.setAddress(address.get());
            } else {
                address = geographyController.createAddress(booking.getAddress().getAddressLine(), booking.getAddress().getZipCode(), booking.getAddress().getCity());
                if (address.isPresent()) {
                    booking.setAddress(address.get());
                }
            }
        }

        //TODO: Ophalen shizzle

        return Optional.of(bookingRepository.save(booking));
    }

    public Optional<Booking> getById(Long id) {
        return bookingRepository.findById(id);
    }

    public Booking updateBooking(Booking updatedBooking) {
        if(!bookingRepository.existsById(updatedBooking.getId())){
            return null;
        }
        return bookingRepository.save(updatedBooking);
    }

    public boolean deleteBooking(Long id) {
        boolean doesExist = bookingRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        bookingRepository.deleteById(id);
        return true;
    }

    public Optional<List<Booking>> getBookingsByToken(HttpServletRequest request) {
        User user = jwtController.userExists(request);
        if( user == null) return  Optional.empty();
        return Optional.ofNullable(bookingRepository.findAllByBooker_Username(user.getUsername()));
    }

    public Optional<List<Booking>> getAllBookings() {
        return Optional.of(bookingRepository.findAll());
    }
}
