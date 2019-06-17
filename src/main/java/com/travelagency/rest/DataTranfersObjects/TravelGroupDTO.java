package com.travelagency.rest.DataTranfersObjects;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;


public class TravelGroupDTO {
    private final Long id;
    private final String name;
    private final Long masterId;
    private final List<User> users;

    public TravelGroupDTO(Long id, @NotNull String name, @NotNull Long masterId, List<User> users) {
        this.id = id;
        this.name = name;
        this.masterId = masterId;
        this.users = users;
    }

    public TravelGroup getTravelGroup() {
        TravelGroup travelGroup = new TravelGroup();
        travelGroup.setName(name);
        travelGroup.setMasterId(masterId);
        travelGroup.setUsers(users);

        return travelGroup;
    }

    public TravelGroup getTravelGroupUpdate() {
        TravelGroup travelGroup = new TravelGroup();
        travelGroup.setId(id);
        travelGroup.setName(name);
        travelGroup.setMasterId(masterId);
        travelGroup.setUsers(users);

        return travelGroup;
    }
}
