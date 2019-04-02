package com.travelagency.model;

import java.util.*;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

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

    @Column(name = "total_price", length = 10)
    @NotNull
    private int total_price;

    @ManyToOne()
    @JoinColumn(name = "discount_id")
    private Discount discount;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "trip_service",
            joinColumns = @JoinColumn(name = "trip_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "service_id", referencedColumnName = "id"))
    private List<Service> services;

    public Trip(@NotNull String name, @NotNull int total_price, Discount discount) {
        this.name = name;
        this.total_price = total_price;
        this.discount = discount;
        services = new ArrayList<>();
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

    public Discount getDiscount() {
        return discount;
    }

    public void setDiscount(Discount discount) {
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
