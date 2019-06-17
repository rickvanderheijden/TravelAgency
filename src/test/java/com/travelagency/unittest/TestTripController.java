package com.travelagency.unittest;

import com.travelagency.controllers.TripController;
import com.travelagency.model.Destination;
import com.travelagency.model.Trip;
import com.travelagency.repository.DestinationRepository;
import com.travelagency.repository.TripRepository;
import com.travelagency.rest.DataTranfersObjects.TripSearchDTO;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TestTripController {
    private static final Long ValidId = 1L;
    private static final Long InvalidId = 2L;

    private final TripRepository tripRepository = Mockito.mock(TripRepository.class);
    private final DestinationRepository destinationRepository = Mockito.mock(DestinationRepository.class);

    private TripController tripController;
    private Trip tripMock;

    private List<Trip> tripsToReturn;
    private List<Destination> destinationsToReturn;

    @Before
    public void setUp() {
        tripController = new TripController(tripRepository, destinationRepository);
        tripMock = Mockito.mock(Trip.class);

        tripsToReturn = new ArrayList<>();
        tripsToReturn.add(new Mockito().mock(Trip.class));
        tripsToReturn.add(new Mockito().mock(Trip.class));
        tripsToReturn.add(new Mockito().mock(Trip.class));

        destinationsToReturn = new ArrayList<>();
        destinationsToReturn.add(new Mockito().mock(Destination.class));
        destinationsToReturn.add(new Mockito().mock(Destination.class));
    }

    @After
    public void tearDown() {
        tripController = null;
    }

    @Test
    public void testCreateTripWithNull() {
        Optional<Trip> trip = tripController.createTrip(null);
        Assert.assertFalse(trip.isPresent());
    }

    @Test
    public void testCreateTripWithDestinationsNull() {
        when(tripMock.getDestinations()).thenReturn(null);
        Optional<Trip> trip = tripController.createTrip(tripMock);
        Assert.assertFalse(trip.isPresent());
    }

    @Test
    public void testCreateTripWithDestinationsValidButNotInRepository() {
        when(tripMock.getDestinations()).thenReturn(destinationsToReturn);
        when(destinationRepository.findById(any())).thenReturn(Optional.empty());
        when(tripRepository.save(any())).thenReturn(tripMock);
        Optional<Trip> trip = tripController.createTrip(tripMock);
        Assert.assertTrue(trip.isPresent());
    }

    @Test
    public void testUpdateTripWithNull() {
        Optional<Trip> trip = tripController.updateTrip(null);
        Assert.assertFalse(trip.isPresent());
    }

    @Test
    public void testUpdateTripWithUnknownTrip() {
        when(tripRepository.existsById(any())).thenReturn(false);
        Optional<Trip> trip = tripController.updateTrip(tripMock);
        Assert.assertFalse(trip.isPresent());
    }

    @Test
    public void testUpdateTripWithKnownTrip() {
        when(tripRepository.existsById(any())).thenReturn(true);
        when(tripRepository.save(any())).thenReturn(tripMock);
        Optional<Trip> trip = tripController.updateTrip(tripMock);
        Assert.assertTrue(trip.isPresent());
    }

    @Test
    public void testDeleteTripWithNull() {
        boolean result = tripController.deleteTrip(null);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteTripWithInvalidId() {
        when(tripRepository.existsById(InvalidId)).thenReturn(false);
        boolean result = tripController.deleteTrip(InvalidId);
        Assert.assertFalse(result);
    }

    @Test
    public void testDeleteTripWithValidId() {
        when(tripRepository.existsById(ValidId)).thenReturn(true);
        boolean result = tripController.deleteTrip(ValidId);
        Assert.assertTrue(result);
    }

    @Test
    public void testGetByIdNull() {
        Optional<Trip> trip = tripController.getById(null);
        Assert.assertFalse(trip.isPresent());
    }

    @Test
    public void testGetByIdValidId() {
        when(tripRepository.findById(ValidId)).thenReturn(Optional.of(tripMock));
        Optional<Trip> trip = tripController.getById(ValidId);
        Assert.assertTrue(trip.isPresent());
    }

    @Test
    public void testGetAllTripsNoTrips() {
        when(tripRepository.findAll(any(Pageable.class))).thenReturn(null);
        Optional<List<Trip>> trips = tripController.getAllTrips();
        Assert.assertTrue(trips.isPresent());
        Assert.assertTrue(trips.get().isEmpty());
    }

    @Test
    public void testGetAllTripsWithTripsLimitZero() {
        when(tripRepository.findAll(any(Pageable.class))).thenReturn(null);
        Optional<List<Trip>> trips = tripController.getAllTrips(0);
        Assert.assertFalse(trips.isPresent());
    }

    @Test
    public void testGetAllTripsWithTripsLimitTen() {
        when(tripRepository.findAll(any(Pageable.class))).thenReturn(new PageImpl<>(tripsToReturn));
        Optional<List<Trip>> trips = tripController.getAllTrips(10);
        Assert.assertTrue(trips.isPresent());
        Assert.assertEquals(tripsToReturn.size(), trips.get().size());
    }

    @Test
    public void searchTripsByKeywordAndContinentOrCountryWithNull() {
        Optional<List<Trip>> trips = tripController.searchTripsByKeywordAndContinentOrCountry(null);
        Assert.assertFalse(trips.isPresent());
    }

    @Test
    public void searchTripsByKeywordAndContinentOrCountryWithKeywordNull() {
        TripSearchDTO tripSearchDTO = new TripSearchDTO("Continent", "Country", new Date(), new Date(), null);
        Optional<List<Trip>> trips = tripController.searchTripsByKeywordAndContinentOrCountry(tripSearchDTO);
        Assert.assertTrue(trips.isPresent());
    }
}
