package com.travelagency.controllers;

import com.travelagency.model.*;
import com.travelagency.repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaymentController {

    private final PaymentRepository paymentRepository;
    private final UserController userController;
    private final BookingController bookingController;


    public PaymentController(
            PaymentRepository paymentRepository, UserController userController, BookingController bookingController) {
        this.paymentRepository = paymentRepository;
        this.userController = userController;
        this.bookingController = bookingController;
    }

    public Optional<Payment> createPayment(Payment payment) {
        if (payment == null) return Optional.empty();

        User user = payment.getUser();
        Booking booking = payment.getBooking();
        if (user == null) return Optional.empty();
        if (booking == null) return Optional.empty();

        Optional<User> userInDatabase = userController.getUserByUsername(user.getUsername());
        Optional<Booking> bookingInDatabase = bookingController.getById(booking.getId());
        if (!userInDatabase.isPresent()){
            return Optional.empty();
        } else {
            payment.setUser(userInDatabase.get());
        }
        if (!bookingInDatabase.isPresent()){
            return Optional.empty();
        } else {
            Booking updateBooking = bookingInDatabase.get();
            updateBooking.setPaid(true);
            Booking updatedBooking = bookingController.updateBooking(updateBooking);

            payment.setBooking(updatedBooking);
        }

        return Optional.of(paymentRepository.save(payment));
    }

    public Payment getById(Long id) {
        return paymentRepository.getOne(id);
    }


    public Optional<List<Payment>> getAllPayments() {
        return Optional.of(paymentRepository.findAll());
    }

    public Optional<List<Payment>> getPaymentsByUsername(String username) {
        return Optional.of(paymentRepository.findByUserUsername(username));
    }

    public Optional<List<Payment>> getPaymentsByBookingId(Long bookingId) {
        return Optional.of(paymentRepository.findAllByBooking_Id(bookingId));
    }

}
