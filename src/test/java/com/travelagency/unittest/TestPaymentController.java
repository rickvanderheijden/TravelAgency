package com.travelagency.unittest;

import com.travelagency.controllers.BookingController;
import com.travelagency.controllers.PaymentController;
import com.travelagency.controllers.UserController;
import com.travelagency.model.*;
import com.travelagency.repository.PaymentRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestPaymentController {

    private final PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
    private final UserController userController = Mockito.mock(UserController.class);
    private final BookingController bookingController = Mockito.mock(BookingController.class);
    private final Payment payment = Mockito.mock(Payment.class);

    private PaymentController paymentController;

    @Before
    public void setUp() {
        paymentController = new PaymentController(paymentRepository, userController, bookingController);
        when(paymentRepository.getOne(any(Long.class))).thenReturn(payment);
    }

    @After
    public void tearDown() {
        paymentController = null;
    }

    @Test
    public void testGetPaymentById() {
        Payment payment = paymentController.getById(1L);
        Assert.assertNotNull(payment);
    }
}
