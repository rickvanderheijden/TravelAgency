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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public int getTotal_price() {
        return total_price;
    }

    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }

    public List<Service> getServices() {
        return services;
    }

    public void setServices(List<Service> services) {
        this.services = services;
    }

    @Column(name = "description", length = 500)
    @NotNull
    private String description;

    @Column(name = "image_url")
    @NotNull
    private String image_url;

    @Column(name = "total_price", length = 10)
    @NotNull
    private int total_price;

    @Column(name = "discount", length = 10)
    private int discount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trip_service",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services;

    public Trip() {
    }

    public Trip(@NotNull String name, @NotNull String description, String image_url, @NotNull int total_price, int discount) {
        this.name = name;
        this.description = description;
        this.image_url = image_url;
        this.total_price = total_price;
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

    public int getTotalPrice() {
        return total_price;
    }

    public void setTotalPrice(int total_price) {
        this.total_price = total_price;
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
