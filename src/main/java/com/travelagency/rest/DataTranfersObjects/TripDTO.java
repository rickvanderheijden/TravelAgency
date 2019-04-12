package com.travelagency.rest.DataTranfersObjects;

import com.travelagency.model.Service;
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
    private final List<Service> services;

    public TripDTO(Long id, String name, String description, String imageUrl, int totalPrice, int discount, List<Service> services) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.services = services;
    }

    public TripDTO(Long id, String name, String description, String imageUrl, int totalPrice, int discount) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.services = new ArrayList<>();
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

    public List<Service> getServices() {
        return services;
    }

    public Trip getTrip (){
        Trip trip = new Trip(){};
        trip.setDescription(this.description);
        trip.setDiscount(this.discount);
        trip.setId(this.getId());
        trip.setImageUrl(this.imageUrl);
        trip.setName(this.name);
        trip.setServices(this.services);
        trip.setTotalPrice(this.totalPrice);
        return trip;
    }
}
