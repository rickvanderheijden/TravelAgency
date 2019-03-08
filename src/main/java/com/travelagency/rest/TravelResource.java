package com.travelagency.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/travel")
public class TravelResource {


    ResponseEntity<?> getTravelById(@PathVariable String id) {
        //TODO: Get Travel from travel manager
        return null;
    }

}
