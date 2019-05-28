package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "address")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "address_line")
    @NotNull
    private String addressLine;

    @ManyToOne
    @JoinColumn(name = "city_id")
    private City city;

    @Column(name = "zip_code")
    @NotNull
    private String zipCode;

    public Address() {}

    public Address(String addressLine, String zipCode, City city) {
        this.addressLine = addressLine;
        this.zipCode = zipCode;
        this.city = city;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddressLine() {
        return this.addressLine;
    }

    public void setAddressLine(String addressLine) {
        this.addressLine = addressLine;
    }

    public City getCity() {
        return this.city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return this.zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Country getCountry() {
        return this.city.getCountry();
    }
}
