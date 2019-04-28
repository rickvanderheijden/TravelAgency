package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @Column(name = "summary", length = 500)
    @NotNull
    private String summary;

    @Column(name = "description", length = 5000)
    @NotNull
    private String description;

    @Column(name = "image_url")
    @NotNull
    private String imageUrl;

    @Column(name = "total_price", length = 10)
    @NotNull
    private int totalPrice;

    @Column(name = "discount", length = 10)
    private int discount;

    @ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.MERGE })
    @JoinTable(name = "trip_tripitem",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "tripitem_id", referencedColumnName = "id"))
    private List<TripItem> tripItems;

    public Trip() {}

    public Trip(
            @NotNull String name,
            @NotNull String description,
            @NotNull String summary,
            String imageUrl,
            @NotNull int totalPrice,
            int discount) {
        this.name = name;
        this.description = description;
        this.summary = summary;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.tripItems = new ArrayList<>();
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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<TripItem> getTripItems() {
        return tripItems;
    }

    public void setTripItems(List<TripItem> tripItems) {
        if (tripItems == null) {
            this.tripItems = new ArrayList<>();
        } else {
            this.tripItems = tripItems;
        }
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
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
}
