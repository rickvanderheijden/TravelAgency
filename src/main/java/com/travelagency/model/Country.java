package com.travelagency.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long Id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    Set<City> cities = new HashSet<>();

    public Country(String name) {
        this.name = name;
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

    public Set<City> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }
}
