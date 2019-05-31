package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "number_of_travelers")
    private int numberOfTravelers = 2;

    @ManyToOne
    @JoinColumn(name = "trip_id")
    @NotNull
    private Trip trip;

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "booking_hotel",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "hotel_id", referencedColumnName = "id"))
    private List<BookableHotel> hotels = new ArrayList<>();

    @ManyToMany(cascade = { CascadeType.MERGE })
    @JoinTable(name = "booking_tripitem",
            joinColumns = @JoinColumn(name = "booking_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tripitem_id", referencedColumnName = "id"))
    private List<TripItem> tripItems = new ArrayList<>();

    @Column(name = "total_price", length = 10)
    private int totalPrice = -1;

    @Column(name = "startdate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date startDate;

    @Column(name = "enddate")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date endDate;

    @Column(name = "booked")
    private boolean booked;

    @Column(name = "paid")
    private boolean paid;

    public Booking() {
    }

    public Booking(@NotNull Trip trip) {
        this.trip = trip;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getNumberOfTravelers() {
        return numberOfTravelers;
    }

    public boolean setNumberOfTravelers(int numberOfTravelers) {
        boolean result = false;

        if (numberOfTravelers > 0) {
            this.numberOfTravelers = numberOfTravelers;
            result = true;
        }

        return result;
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

    public List<BookableHotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<BookableHotel> hotels) {
        this.hotels = hotels;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
