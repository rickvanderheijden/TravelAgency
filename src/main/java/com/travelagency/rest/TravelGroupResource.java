package com.travelagency.rest;

import com.travelagency.controllers.TravelGroupController;
import com.travelagency.controllers.UserController;
import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.rest.DataTranfersObjects.TravelGroupDTO;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("/travelgroups")
public class TravelGroupResource {

    private final TravelGroupController travelGroupController;
    private final UserController userController;

    public TravelGroupResource(TravelGroupController travelGroupController, UserController userController) {
        this.travelGroupController = travelGroupController;
        this.userController = userController;
    }

    @RequestMapping(value = "/users/{id}" , method = RequestMethod.GET)
    public List<User> getUsers(@PathVariable final Long id){
        TravelGroup travelGroup = travelGroupController.getById(id);
        return travelGroupController.getUsersByTravelGroupId(travelGroup);
    }

    @RequestMapping(value = "/createTravelGroup", method = RequestMethod.POST)
    public Optional<TravelGroup> createTrip(@Valid @RequestBody TravelGroupDTO travelGroupDTO) {
        TravelGroup travelGroup = travelGroupDTO.getTravelGroup();
        return travelGroupController.createTravelGroup(travelGroup);
    }

    @RequestMapping(value = "/addUser/{id}", method = RequestMethod.POST)
    public boolean addUser(@Valid @RequestBody TravelGroup travelGroup, @PathVariable final Long id) {
       return userController.addTravelGroup(travelGroup,id);
    }
}
