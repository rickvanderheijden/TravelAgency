package com.travelagency.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "travelgroup")
public class TravelGroup {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "name")
    @NotNull
    private String name;

    @ManyToMany(mappedBy = "travelGroups")
    @JsonIgnore
    private List<User> users;

    public TravelGroup() {
        this.users = new ArrayList<>();
    }

    public TravelGroup(@NotNull String name, List<User> users) {
        this.name = name;
        this.users = users;
    }

    public TravelGroup(@NotNull String name) {
        this.name = name;
        this.users = new ArrayList<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
