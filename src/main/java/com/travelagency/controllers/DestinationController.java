package com.travelagency.controllers;

import com.travelagency.model.City;
import com.travelagency.model.Destination;
import com.travelagency.model.Hotel;
import com.travelagency.model.TripItem;
import com.travelagency.repository.DestinationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DestinationController {

    private final DestinationRepository destinationRepository;
    private final GeographyController geographyController;
    private final HotelController hotelController;
    private final TripItemController tripItemController;

    public DestinationController(
            DestinationRepository destinationRepository,
            GeographyController geographyController,
            HotelController hotelController,
            TripItemController tripItemController) {
        this.destinationRepository= destinationRepository;
        this.geographyController = geographyController;
        this.hotelController = hotelController;
        this.tripItemController = tripItemController;
    }

    public Optional<Destination> createDestination(Destination destination) {
        if (destination == null) return Optional.empty();

        City city = destination.getCity();
        if (city == null) return Optional.empty();

        Optional<City> cityInDatabase = geographyController.getCity(city.getName());
        if (!cityInDatabase.isPresent()){
            return Optional.empty();
        } else {
            destination.setCity(cityInDatabase.get());
        }

        return Optional.of(destinationRepository.save(destination));
    }

    public Destination getById(Long id) {
        return destinationRepository.getOne(id);
    }

    public Optional<Destination> getByCityName(String cityName) {
        return Optional.ofNullable(destinationRepository.getByCityName(cityName));
    }

    public Optional<List<Destination>> getAllDestinations() {
        return Optional.of(destinationRepository.findAll());
    }

    public boolean deleteDestination(long id) {
        boolean doesExist = this.destinationRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.destinationRepository.deleteById(id);
        return true;
    }

    public Destination updateDestination(Long id, Destination destination) {
        if(!this.destinationRepository.existsById(id)){
            return null;
        }

        Destination destinationFromDatabase = this.getById(destination.getId());
        Destination updatedDestination = this.setCityAndHotelAndTripItems(destination);
        destinationFromDatabase.setName(destination.getName());
        destinationFromDatabase.setHotel(updatedDestination.getHotel());
        destinationFromDatabase.setCity(updatedDestination.getCity());
        destinationFromDatabase.setTripItems(updatedDestination.getTripItems());

        return this.destinationRepository.save(destinationFromDatabase);
    }

    private Destination setCityAndHotelAndTripItems(Destination destination) {
        if (destination == null) return null;
        City city = destination.getCity();
        Hotel hotel = destination.getHotel();
        List<TripItem> tripItems = destination.getTripItems();
        destination.setCity(null);
        destination.setHotel(null);
        destination.setTripItems(null);

        Optional<City> cityInDatabase = geographyController.getCity(city.getName());
        Optional<Hotel> hotelInDatabase = hotelController.getHotelByName(hotel.getName());
        for (TripItem tripItem : tripItems){
            Optional<TripItem> tripItemInDatabase = tripItemController.getById(tripItem.getId());
            tripItemInDatabase.ifPresent(destination::addTripItem);
        }
        cityInDatabase.ifPresent(destination::setCity);

        hotelInDatabase.ifPresent(destination::setHotel);

        return destination;
    }
}
