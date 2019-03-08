package com.travelagency.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City implements ICity {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long Id;

    @Column(name = "name")
    private final String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private final Country country;

    public City(String name, Country country){
        this.name = name;
        this.country = country;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Country getCountry() {
        return country;
    }
}
