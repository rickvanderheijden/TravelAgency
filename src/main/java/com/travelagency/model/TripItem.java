package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Range;

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

    @Column(name = "maximum_number_of_attendees", length = 2000)
    @Range(min = 1, max = 1000)
    @NotNull
    private int maximumNumberOfAttendees;

    @Column(name = "minimum_number_of_attendees", length = 2000)
    @Range(min = 1, max = 1000)
    @NotNull
    private int minimumNumberOfAttendees;

    @Column(name = "number_of_attendees", length = 2000)
    @Range(min = 1, max = 1000)
    @NotNull
    private int numberOfAttendees;

    public TripItem() {}

    public TripItem(@NotNull TripItemType tripItemType, @NotNull String name, @NotNull String description, @NotNull String imageBlob, @NotNull Address address, @NotNull int price, @NotNull Date date, @NotNull int numberOfAttendees) {
        this.tripItemType = tripItemType;
        this.name = name;
        this.description = description;
        this.imageBlob = imageBlob;
        this.address = address;
        this.price = price;
        this.date = date;
        this.minimumNumberOfAttendees = 1;
        this.maximumNumberOfAttendees = 8;
        this.numberOfAttendees = numberOfAttendees;
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

    public int getMaximumNumberOfAttendees() { return maximumNumberOfAttendees; }

    public void setMaximumNumberOfAttendees(int maximumNumberOfAttendees) { this.maximumNumberOfAttendees = maximumNumberOfAttendees; }

    public int getMinimumNumberOfAttendees() { return minimumNumberOfAttendees; }

    public void setMinimumNumberOfAttendees(int minimumNumberOfAttendees) { this.minimumNumberOfAttendees = minimumNumberOfAttendees; }

    public List<Destination> getDestinations() { return this.destinations; }

    public void setDestinations(List<Destination> destinations ) { this.destinations = destinations; }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }

    public int getNumberOfAttendees() { return numberOfAttendees; }

    public void setNumberOfAttendees(int numberOfAttendees) { this.numberOfAttendees = numberOfAttendees; }
}
