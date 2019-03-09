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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "trip_id")
    private Trip trip;

    @Column(name = "percentage", length = 10)
    @NotNull
    private int percentage;

    public Discount(Trip trip, @NotNull int percentage) {
        this.trip = trip;
        this.percentage = percentage;
    }

    public Trip getTrip() {
        return trip;
    }

    public void setTrip(Trip trip) {
        this.trip = trip;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
