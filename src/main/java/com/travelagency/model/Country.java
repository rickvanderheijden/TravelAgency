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
    private final String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "continent_id")
    private final Continent continent;

    @OneToMany(mappedBy = "country", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<ICity> cities = new HashSet<>();

    public Country(String name, Continent continent) {
        this.name = name;
        this.continent = continent;
    }

    public boolean addCity(ICity city) {
        if (city == null) return false;

        for (ICity cityInList: cities) {
            if (cityInList.getName().equals(city.getName())) {
                return false;
            }
        }

        return cities.add(city);
    }

    public Set<ICity> getCities() {
        return cities;
    }

    public String getName() {
        return name;
    }

    public Continent getContinent() {
        return continent;
    }
}
