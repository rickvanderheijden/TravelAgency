package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "discount")
public class Discount {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "percentage", length = 10)
    @NotNull
    private int percentage;

    public Discount(@NotNull int percentage) {
        this.percentage = percentage;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
