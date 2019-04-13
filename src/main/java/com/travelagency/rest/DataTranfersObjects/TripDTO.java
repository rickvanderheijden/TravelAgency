package com.travelagency.rest.DataTranfersObjects;

import com.travelagency.model.TripService;
import com.travelagency.model.Trip;

import java.util.ArrayList;
import java.util.List;

public class TripDTO {

    private final Long id;
    private final String name;
    private final String description;
    private final String imageUrl;
    private final int totalPrice;
    private final int discount;
    private final List<TripService> tripServices;

    public TripDTO(Long id, String name, String description, String imageUrl, int totalPrice, int discount, List<TripService> tripServices) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.tripServices = tripServices;
    }

    public TripDTO(Long id, String name, String description, String imageUrl, int totalPrice, int discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.tripServices = new ArrayList<>();
    }

    private Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public int getDiscount() {
        return discount;
    }

    public List<TripService> getServices() {
        return tripServices;
    }

    public Trip getTrip (){
        Trip trip = new Trip(){};
        trip.setDescription(this.description);
        trip.setDiscount(this.discount);
        trip.setId(this.getId());
        trip.setImageUrl(this.imageUrl);
        trip.setName(this.name);
        trip.setTripServices(this.tripServices);
        trip.setTotalPrice(this.totalPrice);
        return trip;
    }
}
