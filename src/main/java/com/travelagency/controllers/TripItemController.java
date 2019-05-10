package com.travelagency.controllers;

import com.travelagency.model.Address;
import com.travelagency.model.TripItem;
import com.travelagency.repository.TripItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TripItemController {

    private final GeographyController geographyController;
    private final TripItemRepository tripItemRepository;

    public TripItemController(TripItemRepository tripItemRepository, GeographyController geographyController) {
        this.tripItemRepository = tripItemRepository;
        this.geographyController = geographyController;
    }

    public Optional<TripItem> createTripItem(TripItem tripItem) {
        if (tripItem == null) return Optional.empty();

        Address address = tripItem.getAddress();
        if (address == null) return Optional.empty();

        Optional<Address> addressInDatabase = geographyController.getAddress(address);
        if (!addressInDatabase.isPresent()) return Optional.empty();

        tripItem.setAddress(addressInDatabase.get());

        return Optional.of(tripItemRepository.save(tripItem));
    }

    public TripItem getById(Long id) {
        return this.tripItemRepository.getOne(id);
    }

    public Optional<TripItem> getFirst() {
        return tripItemRepository.findAll().stream().findFirst();
    }

    public TripItem updateTripItem(TripItem updatedTripItem) {
        if(!this.tripItemRepository.existsById(updatedTripItem.getId())){
            return null;
        }
        return this.tripItemRepository.save(updatedTripItem);
    }

    public boolean deleteTripItem(Long id) {
        boolean doesExist = this.tripItemRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.tripItemRepository.deleteById(id);
        return true;
    }

    public Optional<TripItem> getByCityName(String cityName) {
        return Optional.ofNullable(tripItemRepository.getByAddressCityName(cityName));
    }

    public Optional<List<TripItem>> getAllTripItems() {
        return Optional.of(tripItemRepository.findAll());
    }
}
