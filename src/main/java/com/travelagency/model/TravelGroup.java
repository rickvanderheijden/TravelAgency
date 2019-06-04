package com.travelagency.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cascade;

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

    @Column(name = "masterId")
    @NotNull
    private Long masterId;

    @ManyToMany(mappedBy = "travelGroups")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    @JsonIgnore
    private List<User> users = new ArrayList<>();

    public TravelGroup() {
    }

    public TravelGroup(@NotNull String name, @NotNull Long masterId, List<User> users) {
        this.name = name;
        this.masterId = masterId;
        this.users = users;
    }

    public TravelGroup(@NotNull String name, @NotNull Long masterId) {
        this.name = name;
        this.masterId = masterId;
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

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
