package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tripservice")
public class TripService {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "ServiceType", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private ServiceType serviceType;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "description", length = 2000)
    @NotNull
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Column(name = "price", length = 10)
    @NotNull
    private int price;

    @ManyToMany(mappedBy = "tripServices", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Trip> trips;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    public TripService() {}

    public TripService(@NotNull ServiceType serviceType, @NotNull String name, @NotNull String description, Address address, @NotNull int price, @NotNull Date date) {
        this.serviceType = serviceType;
        this.name = name;
        this.description = description;
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

    public ServiceType getServiceType() {
        return serviceType;
    }

    public void setServiceType(ServiceType serviceType) {
        this.serviceType = serviceType;
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

    public List<Trip> getTrips() { return this.trips; }

    public void setTrips(List<Trip> trips) { this.trips = trips; }
}
