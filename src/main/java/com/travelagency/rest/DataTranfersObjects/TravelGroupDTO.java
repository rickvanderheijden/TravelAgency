package com.travelagency.rest.DataTranfersObjects;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;

import javax.validation.constraints.NotNull;
import java.util.List;


public class TravelGroupDTO {
    private final String name;
    private final Long masterId;
    private List<User> users;

    public TravelGroupDTO(@NotNull String name, @NotNull Long masterId, List<User> users) {
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
}
