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

    @Column(name = "description", length = 500)
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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trip_service",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services;

    public Trip() {
    }

    public Trip(@NotNull String name, @NotNull String description, String imageUrl, @NotNull int totalPrice, int discount) {
        this.name = name;
        this.description = description;
        this.imageUrl = imageUrl;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.services = new ArrayList<>();
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

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public boolean addService(Service service){

        if(services.contains(service))
            return false;

        return services.add(service);
    }

    public boolean removeService(Service service){
        if(!services.contains(service))
            return false;

        return services.remove(service);
    }
}
