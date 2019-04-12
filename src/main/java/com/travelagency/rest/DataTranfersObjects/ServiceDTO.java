package com.travelagency.rest.DataTranfersObjects;

import com.travelagency.model.Address;
import com.travelagency.model.Service;
import com.travelagency.model.ServiceType;
import com.travelagency.model.Trip;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ServiceDTO {

    private final Long id;
    private final ServiceType serviceType;
    private final String name;
    private final Address address;
    private final int price;
    private final List<Trip> trips;
    private final Date date;

    public ServiceDTO(Long id, ServiceType serviceType, String name, Address address, int price, List<Trip> trips, Date date) {
        this.id = id;
        this.serviceType = serviceType;
        this.name = name;
        this.address = address;
        this.price = price;
        this.trips = trips;
        this.date = date;
    }

    public ServiceDTO(Long id, ServiceType serviceType, String name, Address address, int price, Date date) {
        this.id = id;
        this.serviceType = serviceType;
        this.name = name;
        this.address = address;
        this.price = price;
        this.trips = new ArrayList<>();
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public ServiceType getServiceType() {
        return serviceType;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public int getPrice() {
        return price;
    }

    public List<Trip> getTrips() {
        return trips;
    }

    public Date getDate() {
        return date;
    }

    public Service getService(){
        Service service = new Service();

        service.setAddress(this.address);
        service.setDate(this.date);
        service.setId(this.id);
        service.setName(this.name);
        service.setPrice(this.price);
        service.setServiceType(this.serviceType);
        service.setTrips(this.trips);
        return service;
    }
}
