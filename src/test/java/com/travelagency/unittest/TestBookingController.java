package com.travelagency.unittest;

import com.travelagency.controllers.*;
import com.travelagency.model.*;
import com.travelagency.repository.BookingRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

public class TestBookingController {
    private static final Long ValidId = 1L;
    private static final Long InvalidId = 2L;

    private final BookingRepository bookingRepository = Mockito.mock(BookingRepository.class);
    private final BookingItemController bookingItemController = Mockito.mock(BookingItemController.class);
    private final GeographyController geographyController = Mockito.mock(GeographyController.class);
    private final UserController userController = Mockito.mock(UserController.class);
    private final JwtController jwtController = Mockito.mock(JwtController.class);
    private final TravelerController travelerController = Mockito.mock(TravelerController.class);

    private BookingController bookingController;
    private Booking bookingMock;
    private Address addressMock;
    private User bookerMock;
    private HttpServletRequest httpServletRequest;

    private List<Booking> bookingsToReturn;
    private List<BookingItem> bookingItemsToReturn;

    @Before
    public void setUp() {
        bookingController = new BookingController(bookingRepository, bookingItemController, travelerController, geographyController, userController, jwtController);
        bookingMock = Mockito.mock(Booking.class);
        bookerMock = Mockito.mock(User.class);
        addressMock = Mockito.mock(Address.class);
        httpServletRequest = Mockito.mock(HttpServletRequest.class);

        bookingsToReturn = new ArrayList<>();
        bookingsToReturn.add(new Mockito().mock(Booking.class));

        bookingItemsToReturn = new ArrayList<>();
        bookingItemsToReturn.add(new Mockito().mock(BookingItem.class));
        bookingItemsToReturn.add(new Mockito().mock(BookingItem.class));
    }

    @After
    public void tearDown() {
        bookingController = null;
    }

    @Test
    public void testCreateBookingWithNull() {
        Optional<Booking> booking = bookingController.createBooking(null);
        Assert.assertFalse(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsNull() {
        when(bookingMock.getBookingItems()).thenReturn(null);
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertFalse(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsValidAndBookerNull() {
        when(bookingMock.getBookingItems()).thenReturn(bookingItemsToReturn);
        when(bookingMock.getBooker()).thenReturn(null);
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertFalse(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsValidAndBookerValidBookerIdInvalid() {
        when(bookingMock.getBookingItems()).thenReturn(bookingItemsToReturn);
        when(bookingMock.getBooker()).thenReturn(bookerMock);
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertFalse(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsValidAndBookerValidBookerIdValidAddressInvalid() {
        when(bookingMock.getBookingItems()).thenReturn(bookingItemsToReturn);
        when(bookingMock.getBooker()).thenReturn(bookerMock);
        when(userController.getUserById(any())).thenReturn(Optional.of(bookerMock));
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertFalse(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsValidAndBookerValidBookerIdValidAddressInvalidButCreated() {
        when(bookingMock.getBookingItems()).thenReturn(bookingItemsToReturn);
        when(bookingMock.getBooker()).thenReturn(bookerMock);
        when(bookingMock.getAddress()).thenReturn(addressMock);
        when(userController.getUserById(any())).thenReturn(Optional.of(bookerMock));
        when(geographyController.getAddress(any())).thenReturn(Optional.empty());
        when(geographyController.createAddress(any(), any(), any())).thenReturn(Optional.of(addressMock));
        when(bookingRepository.save(any())).thenReturn(bookingMock);
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertTrue(booking.isPresent());
    }

    @Test
    public void testCreateBookingWithBookingItemsValidAndBookerValidBookerIdValidAddressValid() {
        when(bookingMock.getBookingItems()).thenReturn(bookingItemsToReturn);
        when(bookingMock.getBooker()).thenReturn(bookerMock);
        when(bookingMock.getAddress()).thenReturn(addressMock);
        when(userController.getUserById(any())).thenReturn(Optional.of(bookerMock));
        when(geographyController.getAddress(any())).thenReturn(Optional.of(addressMock));
        when(bookingRepository.save(any())).thenReturn(bookingMock);
        Optional<Booking> booking = bookingController.createBooking(bookingMock);
        Assert.assertTrue(booking.isPresent());
    }

    @Test
    public void testGetBookingsByTokenNull() {
        Optional<List<Booking>> result = bookingController.getBookingsByToken(null);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testGetBookingsByTokenValidUserInvalid() {
        when(jwtController.userExists(any())).thenReturn(null);
        Optional<List<Booking>> result = bookingController.getBookingsByToken(httpServletRequest);
        Assert.assertFalse(result.isPresent());
    }

    @Test
    public void testGetBookingsByTokenValidUserValid() {
        when(jwtController.userExists(any())).thenReturn(bookerMock);
        when(bookingRepository.findAllByBooker_Username(any())).thenReturn(bookingsToReturn);
        Optional<List<Booking>> result = bookingController.getBookingsByToken(httpServletRequest);
        Assert.assertTrue(result.isPresent());
    }

    @Test
    public void testUpdateBookingNull() {
        Booking result = bookingController.updateBooking(null);
        Assert.assertNull(result);
    }

    @Test
    public void testUpdateBookingInvalid() {
        when(bookingRepository.existsById(any())).thenReturn(false);
        Booking result = bookingController.updateBooking(bookingMock);
        Assert.assertNull(result);
    }

    @Test
    public void testUpdateBookingValid() {
        when(bookingRepository.existsById(any())).thenReturn(true);
        when(bookingRepository.save(any())).thenReturn(bookingMock);
        Booking result = bookingController.updateBooking(bookingMock);
        Assert.assertNotNull(result);
    }

    @Test
    public void testDeleteBookingNull() {
        boolean result = bookingController.deleteBooking(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteBookingInvalid() {
        when(bookingRepository.existsById(ValidId)).thenReturn(false);
        boolean result = bookingController.deleteBooking(ValidId);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteBookingValid() {
        when(bookingRepository.existsById(ValidId)).thenReturn(true);
        when(bookingRepository.save(any())).thenReturn(bookingMock);
        boolean result = bookingController.deleteBooking(ValidId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetByIdNull() {
        Optional<Booking> result = bookingController.getById(null);
        Assert.assertFalse(result.isPresent());

    }

    @Test
    public void testGetByIdValidId() {
        when(bookingRepository.findById(ValidId)).thenReturn(Optional.of(bookingMock));
        Optional<Booking> result = bookingController.getById(ValidId);
        Assert.assertTrue(result.isPresent());

    }
}
