package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tripitem")
public class TripItem {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "tripitem_type", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private TripItemType tripItemType;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description", length = 2000)
    @NotNull
    private String description;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "price", length = 10)
    @NotNull
    private int price;

    @ManyToMany(mappedBy = "tripItems", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Trip> trips;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    public TripItem() {}

    public TripItem(@NotNull TripItemType tripItemType, @NotNull String name, @NotNull String description, @NotNull String imageUrl, @NotNull Address address, @NotNull int price, @NotNull Date date) {
        this.tripItemType = tripItemType;
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.address = address;
        this.price = price;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TripItemType getTripItemType() {
        return tripItemType;
    }

    public void setTripItemType(TripItemType tripItemType) {
        this.tripItemType = tripItemType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Trip> getTrips() { return this.trips; }

    public void setTrips(List<Trip> trips) { this.trips = trips; }
}