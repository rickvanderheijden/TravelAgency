package com.travelagency.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "traveler")
public class Traveler {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "firstName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstName;

    @Column(name = "lastName", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
