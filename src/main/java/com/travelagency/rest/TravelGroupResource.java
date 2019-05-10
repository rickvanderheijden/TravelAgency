package com.travelagency.rest;

import com.travelagency.controllers.TravelGroupController;
import com.travelagency.model.TravelGroup;
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

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Optional<List<TravelGroup>> getAll() { return travelGroupController.getAllTravelGroups(); }

}
