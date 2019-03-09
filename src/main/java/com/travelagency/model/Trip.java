package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "trip")
public class Trip {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name", length = 255)
    @NotNull
    private String name;

    @Column(name = "total_price", length = 10)
    @NotNull
    private int total_price;

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "discount_id")
    private Discount discount;

    public Trip(@NotNull String name, @NotNull int total_price, Discount discount) {
        this.name = name;
        this.total_price = total_price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
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
}
