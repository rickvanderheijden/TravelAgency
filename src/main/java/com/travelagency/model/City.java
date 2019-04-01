package com.travelagency.model;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private final String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private final Country country;

    public City(String name, Country country){
        this.name = name;
        this.country = country;
    }

    public Long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}
