package com.travelagency.rest;

import com.travelagency.controllers.ContinentController;
import com.travelagency.model.Continent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/continents")
public class ContinentResource {

    private final ContinentController continentController;

    public ContinentResource(ContinentController continentController) {
        this.continentController = continentController;
    }

    @RequestMapping(value = "/all", method = RequestMethod.GET)
    public Optional<List<Continent>> getAll() {
        return this.continentController.getAllContinents();
    }
}
