package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "country_id")
    @JsonIgnore
    private Country country;

    public City() {}

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
