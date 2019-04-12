package com.travelagency.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "continent_id")
    @JsonIgnore
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<City> cities = new HashSet<>();

    public Country() {}

    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }

    public boolean addCity(City city) {
        if (city == null) return false;

        for (City cityInList: cities) {
            if (cityInList.getName().equals(city.getName())) {
                return false;
            }
        }

        return cities.add(city);
    }

    public Long getId() {
        return id;
    }

    public Set<City> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }
}
