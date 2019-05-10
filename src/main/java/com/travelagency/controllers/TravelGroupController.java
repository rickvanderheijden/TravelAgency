package com.travelagency.controllers;

import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.repository.TravelGroupRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelGroupController {

    private final TravelGroupRepository travelGroupRepository;

    public TravelGroupController(TravelGroupRepository travelGroupRepository) {
        this.travelGroupRepository = travelGroupRepository;
    }

    public Optional<List<TravelGroup>> getAllTravelGroups() {
        return Optional.of(travelGroupRepository.findAll());
    }

    public Optional<TravelGroup> createTravelGroup(TravelGroup travelGroup){
        return Optional.of(travelGroupRepository.save(travelGroup));
    }

    public Optional<TravelGroup> createTravelGroup(String travelGroupName){
        if(travelGroupName == null) return Optional.empty();

        TravelGroup travelGroup = new TravelGroup(travelGroupName);
        return Optional.of(travelGroupRepository.save(travelGroup));
    }

    public TravelGroup getById(Long id) { return  this.travelGroupRepository.getOne(id); }

    public TravelGroup updateTravelGroup(TravelGroup updatedTravelGroup) {
        if(!this.travelGroupRepository.existsById(updatedTravelGroup.getId())){
            return  null;
        }
        return this.travelGroupRepository.save(updatedTravelGroup);
    }

    public  boolean deleteTravelGroup (Long id) {
        boolean doesExist = this.travelGroupRepository.existsById(id);
        if(!doesExist)
            return false;

        this.travelGroupRepository.deleteById(id);
        return true;
    }

    public Optional<TravelGroup> getTravelGroupByName(String name) {
        return Optional.ofNullable(travelGroupRepository.findByName(name));
    }
}
