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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestPaymentController {
    private static final Long ValidId = 1L;
    private static final Long InvalidId = 2L;

    private final PaymentRepository paymentRepository = Mockito.mock(PaymentRepository.class);
    private final UserController userController = Mockito.mock(UserController.class);
    private final BookingController bookingController = Mockito.mock(BookingController.class);

    private PaymentController paymentController;

    List<Payment> paymentsToReturn;

    @Before
    public void setUp() {
        paymentController = new PaymentController(paymentRepository, userController, bookingController);

        paymentsToReturn = new ArrayList<>();
        paymentsToReturn.add(Mockito.mock(Payment.class));
        paymentsToReturn.add(Mockito.mock(Payment.class));

        when(paymentRepository.findAllByBookingId(ValidId)).thenReturn(paymentsToReturn);
        when(paymentRepository.findAllByUserId(ValidId)).thenReturn(paymentsToReturn);
        when(paymentRepository.getOne(ValidId)).thenReturn(Mockito.mock(Payment.class));
        when(paymentRepository.getOne(InvalidId)).thenReturn(null);
    }

    @After
    public void tearDown() {
        paymentController = null;
    }

    @Test
    public void testCreatePaymentWithNull() {
        Optional<Payment> payment = paymentController.createPayment(null);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testCreatePaymentWithUserIdNull() {
        Payment paymentMock = Mockito.mock(Payment.class);
        when(paymentMock.getUserId()).thenReturn(null);

        Optional<Payment> payment = paymentController.createPayment(paymentMock);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testCreatePaymentWithBookingIdNull() {
        Payment paymentMock = Mockito.mock(Payment.class);
        when(paymentMock.getUserId()).thenReturn(1L);
        when(paymentMock.getBookingId()).thenReturn(null);

        Optional<Payment> payment = paymentController.createPayment(paymentMock);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testCreatePaymentWithInvalidUserId() {
        Long userId = 1L;
        Long bookingId = 2L;
        Payment paymentMock = Mockito.mock(Payment.class);
        when(paymentMock.getUserId()).thenReturn(userId);
        when(paymentMock.getBookingId()).thenReturn(bookingId);
        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(userId);
        when(userController.getUserById(userId)).thenReturn(Optional.empty());

        Optional<Payment> payment = paymentController.createPayment(paymentMock);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testCreatePaymentWithValidUserIdAndInvalidBookingId() {
        Long userId = 1L;
        Long bookingId = 2L;
        Payment paymentMock = Mockito.mock(Payment.class);
        when(paymentMock.getUserId()).thenReturn(userId);
        when(paymentMock.getBookingId()).thenReturn(bookingId);
        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(userId);
        when(userController.getUserById(userId)).thenReturn(Optional.of(user));

        Optional<Payment> payment = paymentController.createPayment(paymentMock);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testCreatePaymentWithValidUserIdAndValidBookingId() {
        Long userId = 1L;
        Long bookingId = 2L;
        Payment paymentMock = Mockito.mock(Payment.class);
        when(paymentMock.getUserId()).thenReturn(userId);
        when(paymentMock.getBookingId()).thenReturn(bookingId);
        User user = Mockito.mock(User.class);
        when(user.getId()).thenReturn(userId);
        Booking booking = Mockito.mock(Booking.class);
        when(booking.getId()).thenReturn(bookingId);
        when(userController.getUserById(userId)).thenReturn(Optional.of(user));
        when(bookingController.getById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingController.updateBooking(any())).thenReturn(booking);
        when(paymentRepository.save(any())).thenReturn(paymentMock);

        Optional<Payment> payment = paymentController.createPayment(paymentMock);
        Assert.assertTrue(payment.isPresent());
    }

    @Test
    public void testGetPaymentByIdWithValidId() {
        Optional<Payment> payment = paymentController.getById(ValidId);
        Assert.assertTrue(payment.isPresent());
    }

    @Test
    public void testGetPaymentByIdWithInvalidId() {
        Optional<Payment> payment = paymentController.getById(InvalidId);
        Assert.assertFalse(payment.isPresent());
    }

    @Test
    public void testGetAllPaymentsWithEntries() {
        when(paymentRepository.findAll()).thenReturn(paymentsToReturn);
        Optional<List<Payment>> payments = paymentController.getAllPayments();
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(paymentsToReturn.size(), payments.get().size());
    }

    @Test
    public void testGetAllPaymentsWithNoEntries() {
        Optional<List<Payment>> payments = paymentController.getAllPayments();
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(0, payments.get().size());
    }

    @Test
    public void testGetPaymentsByUserIdWithValidUserId() {
        Optional<List<Payment>> payments = paymentController.getPaymentsByUserId(ValidId);
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(paymentsToReturn.size(), payments.get().size());
    }

    @Test
    public void testGetPaymentsByUserIdWithInvalidUserId() {
        Optional<List<Payment>> payments = paymentController.getPaymentsByUserId(InvalidId);
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(0, payments.get().size());
    }

    @Test
    public void testGetPaymentsByBookingIdWithValidBookingId() {
        Optional<List<Payment>> payments = paymentController.getPaymentsByBookingId(ValidId);
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(paymentsToReturn.size(), payments.get().size());
    }

    @Test
    public void testGetPaymentsByBookingIdWithInvalidBookingId() {
        Optional<List<Payment>> payments = paymentController.getPaymentsByBookingId(InvalidId);
        Assert.assertTrue(payments.isPresent());
        Assert.assertEquals(0, payments.get().size());
    }
}
