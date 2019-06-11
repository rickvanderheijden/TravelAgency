package com.travelagency.rest;

import com.travelagency.controllers.PaymentController;
import com.travelagency.model.Payment;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/payments")
public class PaymentResource {

    private final PaymentController paymentController;

    public PaymentResource(PaymentController paymentController) {
        this.paymentController = paymentController;
    }

    @PostMapping()
    public Optional<Payment> createBooking(@Valid @RequestBody Payment payment) {
        return paymentController.createPayment(payment);
    }

    @GetMapping(value = "/{id}")
    public Payment getById(@PathVariable final Long id) {
        return paymentController.getById(id);
    }

    @GetMapping()
    public Optional<List<Payment>> getAllPayments() {
        return paymentController.getAllPayments();
    }

    @GetMapping(value = "/booking/{Id}")
    public Optional<List<Payment>> getByBookingId(@PathVariable final Long id) {
        return paymentController.getPaymentsByBookingId(id);
    }

    @GetMapping(value = "/user/{userId}")
    public Optional<List<Payment>> getByUserId(@PathVariable final Long userId) {
        return paymentController.getPaymentsByUserId(userId);
    }
}
