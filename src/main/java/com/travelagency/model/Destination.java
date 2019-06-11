package com.travelagency.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("SpellCheckingInspection")
@Entity
@Table(name = "destination")
public class Destination {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @ManyToOne
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    @ManyToMany
    @JoinTable(name = "destination_tripitem",
            joinColumns = @JoinColumn(name = "destination_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tripitem_id", referencedColumnName = "id"))
    private List<TripItem> tripItems = new ArrayList<>();

    public Destination() {}

    public Destination(City city) {
        this.city = city;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public boolean addTripItem(TripItem tripItem) {
        if (tripItem == null) return false;
        if (tripItems.contains(tripItem)) return false;

        return tripItems.add(tripItem);
    }

    public List<TripItem> getTripItems() {
        return tripItems;
    }

    public void setTripItems(List<TripItem> tripItems) {
        if (tripItems == null) {
            this.tripItems = new ArrayList<>();
        } else {
            this.tripItems = tripItems;
        }
    }
}
