package com.travelagency.rest;

import com.travelagency.controllers.TravelGroupController;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @RequestMapping(value = "/createTravelGroup", method = RequestMethod.POST)
    public Optional<TravelGroup> createTrip(@Valid @RequestBody TravelGroup travelGroup) {
        return travelGroupController.createTravelGroup(travelGroup);
    }
}
