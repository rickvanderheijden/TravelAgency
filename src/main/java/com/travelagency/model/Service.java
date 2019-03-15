package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "trip")
public class Service {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "ServiceTypeId")
    @NotNull
    private int serviceTypeId;

    @Column(name = "name")
    @NotNull
    private String name;

    private Address address;

    @Column(name = "price", length = 10)
    @NotNull
    private int price;

    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date date;

    public Service(@NotNull int serviceTypeId, @NotNull String name, Address address, @NotNull int price, @NotNull Date date) {
        this.serviceTypeId = serviceTypeId;
        this.name = name;
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

    public int getServiceTypeId() {
        return serviceTypeId;
    }

    public void setServiceTypeId(int serviceTypeId) {
        this.serviceTypeId = serviceTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
