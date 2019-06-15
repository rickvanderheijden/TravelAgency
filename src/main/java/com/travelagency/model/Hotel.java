package com.travelagency.model;

import com.travelagency.util.Dateparser;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "hotel")
public class Hotel {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

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

    @Column(name = "available_from")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date availableFrom;

    @Column(name = "available_to")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    private Date availableTo;

    public Hotel() {}

    public Hotel(@NotNull String name, @NotNull String description, @NotNull Address address, @NotNull int price, @NotNull String availableFrom, @NotNull String availableTo) {
        this.name = name;
        this.description = description;
        this.address = address;
        this.price = price;
        this.availableFrom = Dateparser.parseDate(availableFrom);
        this.availableTo = Dateparser.parseDate(availableTo);
    }

    public Hotel(@NotNull String name, @NotNull String description, String imageBlob, Address address, @NotNull int price, @NotNull String availableFrom, @NotNull String availableTo) {
        this.name = name;
        this.description = description;
        this.imageBlob = imageBlob;
        this.address = address;
        this.price = price;
        this.availableFrom = Dateparser.parseDate(availableFrom);
        this.availableTo = Dateparser.parseDate(availableTo);
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

    public Date getAvailableFrom() {
        return this.availableFrom;
    }

    public void setAvailableFrom(Date availableFrom) {
        this.availableFrom = availableFrom;
    }

    public Date getAvailableTo() {
        return availableTo;
    }

    public void setAvailableTo(Date availableTo) {
        this.availableTo = availableTo;
    }

    public String getImageBlob() {
        return imageBlob;
    }

    public void setImageBlob(String imageBlob) {
        this.imageBlob = imageBlob;
    }

    public int getAvailability() {
        return 4;  //TODO: Calculate with reservations and availableFrom
    }
}
