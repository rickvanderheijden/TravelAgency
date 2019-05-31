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

    @Column(name = "image_blob")
    @Lob
    private String imageBlob;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "price", length = 10)
    @NotNull
    private int price;

    @ManyToMany(mappedBy = "tripItems", fetch = FetchType.EAGER)
    @JsonIgnore
    private List<Destination> destinations;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    @Column(name = "max_persons", length = 10)
    private int maxPersons;

    @Column(name = "min_persons", length = 10)
    private int minPersons;

    public TripItem() {}

    public TripItem(@NotNull TripItemType tripItemType, @NotNull String name, @NotNull String description, @NotNull String imageBlob, @NotNull Address address, @NotNull int price, @NotNull Date date) {
        this.tripItemType = tripItemType;
        this.name = name;
        this.description = description;
        this.imageBlob = imageBlob;
        this.address = address;
        this.price = price;
        this.date = date;
        this.minPersons = 1;
        this.maxPersons = 8;
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

    public int getMaxPersons() { return maxPersons; }

    public void setMaxPersons(int maxPersons) { this.maxPersons = maxPersons; }

    public int getMinPersons() { return minPersons; }

    public void setMinPersons(int minPersons) { this.minPersons = minPersons; }

    public List<Destination> getDestinations() { return this.destinations; }

    public void setDestinations(List<Destination> destinations ) { this.destinations = destinations; }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }
}
