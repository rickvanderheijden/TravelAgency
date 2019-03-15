package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "continent")
public class Address {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String address;

    private City city;

    @Column(name = "zip_code")
    @NotNull
    private String zipCode;

    private Country country;

    public Address(@NotNull String address, City city, @NotNull String zipCode, Country country) {
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
        this.country =  country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }
}
