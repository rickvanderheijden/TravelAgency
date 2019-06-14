package com.travelagency.unittest;

import com.travelagency.controllers.*;
import com.travelagency.model.*;
import com.travelagency.repository.HotelRepository;
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

public class TestHotelController {
    private static final Long ValidId = 1L;
    private static final Long InvalidId = 2L;

    private final HotelRepository hotelRepository = Mockito.mock(HotelRepository.class);
    private final GeographyController geographyController = Mockito.mock(GeographyController.class);

    private HotelController hotelController;
    private Hotel hotelMock;
    private City cityMock;
    private Address addressMock;
    private List<Hotel> hotelsToReturn;

    @Before
    public void setUp() {
        hotelController = new HotelController(hotelRepository, geographyController);
        hotelMock = Mockito.mock(Hotel.class);
        cityMock = Mockito.mock(City.class);
        addressMock = Mockito.mock(Address.class);

        hotelsToReturn = new ArrayList<>();
        hotelsToReturn.add(Mockito.mock(Hotel.class));
        hotelsToReturn.add(Mockito.mock(Hotel.class));
    }

    @After
    public void tearDown() {
        hotelController = null;
    }

    @Test
    public void testCreateHotelWithNull() {
        Optional<Hotel> hotel = hotelController.createHotel(null);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testCreateHotelWithAddressNull() {
        when(hotelMock.getAddress()).thenReturn(null);

        Optional<Hotel> hotel = hotelController.createHotel(hotelMock);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testCreateHotelWithAddressInDatabase() {
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.of(addressMock));
        when(geographyController.getCity(any())).thenReturn(Optional.empty());
        when(hotelRepository.save(any())).thenReturn(hotelMock);

        Optional<Hotel> hotel = hotelController.createHotel(hotelMock);
        Assert.assertTrue(hotel.isPresent());
    }

    @Test
    public void testCreateHotelWithAddressNotInDatabaseAndCityUnknown() {
        when(cityMock.getName()).thenReturn("");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getCity(any())).thenReturn(Optional.empty());

        Optional<Hotel> hotel = hotelController.createHotel(hotelMock);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testCreateHotelWithAddressNotInDatabaseAndCityKnown() {
        when(cityMock.getName()).thenReturn("");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getCity(any())).thenReturn(Optional.of(cityMock));
        when(hotelRepository.save(any())).thenReturn(hotelMock);

        Optional<Hotel> hotel = hotelController.createHotel(hotelMock);
        Assert.assertTrue(hotel.isPresent());
    }


    @Test
    public void testUpdateHotelWithNull() {
        Optional<Hotel> hotel = hotelController.updateHotel(null);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testUpdateHotelWithAddressNull() {
        when(hotelMock.getAddress()).thenReturn(null);

        Optional<Hotel> hotel = hotelController.updateHotel(hotelMock);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testUpdateHotelWithAddressInDatabase() {
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getAddress(any())).thenReturn(Optional.of(addressMock));
        when(geographyController.getCity(any())).thenReturn(Optional.empty());
        when(hotelRepository.save(any())).thenReturn(hotelMock);

        Optional<Hotel> hotel = hotelController.updateHotel(hotelMock);
        Assert.assertTrue(hotel.isPresent());
    }

    @Test
    public void testUpdateHotelWithAddressNotInDatabaseAndCityUnknown() {
        when(cityMock.getName()).thenReturn("");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getCity(any())).thenReturn(Optional.empty());

        Optional<Hotel> hotel = hotelController.updateHotel(hotelMock);
        Assert.assertFalse(hotel.isPresent());
    }

    @Test
    public void testUpdateHotelWithAddressNotInDatabaseAndCityKnown() {
        when(cityMock.getName()).thenReturn("");
        when(addressMock.getCity()).thenReturn(cityMock);
        when(hotelMock.getAddress()).thenReturn(addressMock);
        when(geographyController.getCity(any())).thenReturn(Optional.of(cityMock));
        when(hotelRepository.save(any())).thenReturn(hotelMock);

        Optional<Hotel> hotel = hotelController.updateHotel(hotelMock);
        Assert.assertTrue(hotel.isPresent());
    }

    @Test
    public void testDeleteHotelIdNull() {
        boolean result = hotelController.deleteHotel(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteHotelInvalidId() {
        when(hotelRepository.existsById(InvalidId)).thenReturn(false);

        boolean result = hotelController.deleteHotel(InvalidId);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteHotelValidId() {
        when(hotelRepository.existsById(ValidId)).thenReturn(true);

        boolean result = hotelController.deleteHotel(ValidId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetByCityNameNull() {
        Optional<List<Hotel>> hotels = hotelController.getByCityName(null);
        Assert.assertTrue(hotels.isPresent());
        Assert.assertEquals(0, hotels.get().size());
    }

    @Test
    public void testGetByCityNameUnknown() {
        when(geographyController.getCity(any())).thenReturn(null);
        Optional<List<Hotel>> hotels = hotelController.getByCityName(null);
        Assert.assertTrue(hotels.isPresent());
        Assert.assertEquals(0, hotels.get().size());
    }

    @Test
    public void testGetByCityNameKnown() {
        when(cityMock.getName()).thenReturn("");
        when(geographyController.getCity(any())).thenReturn(Optional.of(cityMock));
        when(hotelRepository.getByAddressCityName(any())).thenReturn(hotelsToReturn);
        Optional<List<Hotel>> hotels = hotelController.getByCityName(cityMock.getName());
        Assert.assertTrue(hotels.isPresent());
        Assert.assertEquals(hotelsToReturn.size(), hotels.get().size());
    }

    @Test
    public void testGetAllHotelNoHotels() {
        Optional<List<Hotel>> hotels = hotelController.getAllHotels();
        Assert.assertTrue(hotels.isPresent());
        Assert.assertEquals(0, hotels.get().size());
    }

    @Test
    public void testGetAllHotelWithHotels() {
        when(hotelRepository.findAll()).thenReturn(hotelsToReturn);

        Optional<List<Hotel>> hotels = hotelController.getAllHotels();
        Assert.assertTrue(hotels.isPresent());
        Assert.assertEquals(hotelsToReturn.size(), hotels.get().size());
    }

    @Test
    public void testGetAvailabilityNull() {
        int expectedResult = 0;
        int result = hotelController.getAvailability(null);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAvailabilityUnknownId() {
        when(hotelRepository.getOne(InvalidId)).thenReturn(null);

        int expectedResult = 0;
        int result = hotelController.getAvailability(InvalidId);
        Assert.assertEquals(expectedResult, result);
    }

    @Test
    public void testGetAvailabilityKnownId() {
        int expectedResult = 12;
        when(hotelRepository.getOne(ValidId)).thenReturn(hotelMock);
        when(hotelMock.getAvailability()).thenReturn(expectedResult);

        int result = hotelController.getAvailability(ValidId);
        Assert.assertEquals(expectedResult, result);
    }
}
