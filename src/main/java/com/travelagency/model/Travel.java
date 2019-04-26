package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "travel")
public class Travel {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @NotNull
    private Trip trip;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "travel_tripitem",
            joinColumns = @JoinColumn(name = "travel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tripitem_id", referencedColumnName = "id"))
    private List<TripItem> tripItems = new ArrayList<>();

    @Column(name = "total_price", length = 10)
    private int totalPrice = -1;

    @Column(name = "booked")
    private boolean booked;

    @Column(name = "paid")
    private boolean paid;

    public Travel() {
    }

    public Travel(@NotNull Trip trip) {
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public Trip getTrip() {
        return this.trip;
    }

    public int getTotalPrice() {
        if (totalPrice < 0) {
            return trip.getTotalPrice();
        }

        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<TripItem> getTripItems() {
        return this.tripItems;
    }

    public void setTripItems(List<TripItem> tripItems) {
        this.tripItems = tripItems;
    }

    public boolean addTripItem(TripItem tripItem){
        if(tripItems.contains(tripItem))
            return false;

        return tripItems.add(tripItem);
    }

    public boolean removeTripItem(TripItem tripItem){
        if(!tripItems.contains(tripItem))
            return false;

        return tripItems.remove(tripItem);
    }

    public boolean isPaid() {
        return paid;
    }

    public void setPaid(boolean paid) {
        this.paid = paid;
    }

    public boolean isBooked() {
        return booked;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
