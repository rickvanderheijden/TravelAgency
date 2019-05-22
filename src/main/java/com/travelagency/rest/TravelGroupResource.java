package com.travelagency.rest;

import com.travelagency.controllers.TravelGroupController;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/travelgroups")
public class TravelGroupResource {

    private final TravelGroupController travelGroupController;

    public TravelGroupResource(TravelGroupController travelGroupController) {
        this.travelGroupController = travelGroupController;
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.GET)
    public List<User> getUsers(@PathVariable final Long id){
        TravelGroup travelGroup = travelGroupController.getById(id);
        return travelGroupController.getUsersByTravelGroupId(travelGroup);
    }
}
