package com.travelagency.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "continent")
public class Continent {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "continent", cascade = CascadeType.ALL, orphanRemoval = true)
    private final Set<Country> countries = new HashSet<>();

    public Continent(String name) {
        this.name = name;
    }

    public boolean addCountry(Country country) {
        if (country == null) return false;

        for (Country countryInList : countries) {
            if (countryInList.getName().equals(country.getName())) {
                return false;
            }
        }

        return countries.add(country);
    }

    public String getName() {
        return name;
    }

    public Set<Country> getCountries() {
        return countries;
    }
}
