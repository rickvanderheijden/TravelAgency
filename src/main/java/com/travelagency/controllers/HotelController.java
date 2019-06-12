package com.travelagency.controllers;

import com.travelagency.model.Address;
import com.travelagency.model.City;
import com.travelagency.model.Hotel;
import com.travelagency.repository.HotelRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class HotelController {

    private final HotelRepository hotelRepository;
    private final GeographyController geographyController;

    public HotelController(HotelRepository hotelRepository, GeographyController geographyController) {
        this.hotelRepository = hotelRepository;
        this.geographyController = geographyController;
    }

    public Optional<Hotel> createHotel(Hotel hotel) {
        return updateHotel(hotel);
    }

    public Hotel getById(Long id) {
        return hotelRepository.getOne(id);
    }

    public Optional<Hotel> updateHotel(Hotel hotel) {
        if (hotel == null) return Optional.empty();

        Address address = hotel.getAddress();
        if (address == null) return Optional.empty();

        Optional<Address> addressInDatabase = geographyController.getAddress(address);
        if (!addressInDatabase.isPresent()){
            Optional<City> optionalCity = geographyController.getCity(address.getCity().getName());
            if(!optionalCity.isPresent()){
                return Optional.empty();
            }
            Optional<Address> createdAddress = geographyController.createAddress(address.getAddressLine(), address.getZipCode(), optionalCity.get());
            createdAddress.ifPresent(hotel::setAddress);
        } else {
            hotel.setAddress(addressInDatabase.get());
        }

        return Optional.of(hotelRepository.save(hotel));
    }

    public boolean deleteHotel(Long id) {
        if (!hotelRepository.existsById(id)) return false;
        hotelRepository.deleteById(id);
        return true;
    }

    public Optional<List<Hotel>> getByCityName(String cityName) {
        return Optional.ofNullable(hotelRepository.getByAddressCityName(cityName));
    }

    public Optional<List<Hotel>> getAllHotels() {
        return Optional.of(hotelRepository.findAll());
    }

    public Optional<Hotel> getFirstByAddressCityName(String name) {
        return Optional.ofNullable(hotelRepository.getFirstByAddressCityName(name));
    }

    public Optional<Hotel> getHotelByName(String name) {
        return Optional.ofNullable(hotelRepository.getFirstByName(name));
    }

    public int getAvailability(Long id) {
        if (id == null) return 0;

        Hotel hotel = getById(id);
        if (hotel == null) return 0;

        return hotel.getAvailability();
    }
}
