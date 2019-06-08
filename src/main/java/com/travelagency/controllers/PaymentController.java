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

        Long userId = payment.getUserId();
        Long bookingId = payment.getBookingId();
        if (userId == null) return Optional.empty();
        if (bookingId == null) return Optional.empty();

        Optional<User> userInDatabase = userController.getUserById(userId);
        Optional<Booking> bookingInDatabase = bookingController.getById(bookingId);
        if (!userInDatabase.isPresent()){
            return Optional.empty();
        } else {
            payment.setUserId(userInDatabase.get().getId());
        }
        if (!bookingInDatabase.isPresent()){
            return Optional.empty();
        } else {
            Booking updateBooking = bookingInDatabase.get();
            updateBooking.setPaid(true);
            Booking updatedBooking = bookingController.updateBooking(updateBooking);

            payment.setBookingId(updatedBooking.getId());
        }

        Payment saved = paymentRepository.save(payment);
        bookingInDatabase.get().addPayment(saved);
        bookingController.updateBooking(bookingInDatabase.get());

        return Optional.of(saved);

    }

    public Payment getById(Long id) {
        return paymentRepository.getOne(id);
    }


    public Optional<List<Payment>> getAllPayments() {
        return Optional.of(paymentRepository.findAll());
    }

    public Optional<List<Payment>> getPaymentsByUserId(Long userId) {
        return Optional.of(paymentRepository.findAllByUserId(userId));
    }

    public Optional<List<Payment>> getPaymentsByBookingId(Long bookingId) {
        return Optional.of(paymentRepository.findAllByBookingId(bookingId));
    }

}
