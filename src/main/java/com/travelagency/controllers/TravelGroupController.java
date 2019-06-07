package com.travelagency.controllers;

import com.travelagency.model.TravelGroup;
import com.travelagency.model.User;
import com.travelagency.repository.TravelGroupRepository;
import com.travelagency.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TravelGroupController {

    private final TravelGroupRepository travelGroupRepository;
    private final UserRepository userRepository;
    private final UserController userController;

    public TravelGroupController(TravelGroupRepository travelGroupRepository, UserRepository userRepository, UserController userController) {
        this.travelGroupRepository = travelGroupRepository;
        this.userRepository = userRepository;
        this.userController = userController;
    }

    public Optional<List<TravelGroup>> getAllTravelGroups() {
        return Optional.of(travelGroupRepository.findAll());
    }

    public Optional<TravelGroup> createTravelGroup(TravelGroup travelGroup){
        Optional<TravelGroup> returnTravelGroup = Optional.of(travelGroupRepository.save(travelGroup));

        for (User user: travelGroup.getUsers())
            this.userController.addTravelGroup(travelGroup, user.getId());

        return returnTravelGroup;
    }

    public Optional<TravelGroup> createTravelGroup(String travelGroupName, Long masterId){
        if(travelGroupName == null) return Optional.empty();

        TravelGroup travelGroup = new TravelGroup(travelGroupName, masterId);
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

    public List<User> getUsersByTravelGroupId(TravelGroup travelGroup) {
        return  this.userRepository.findByTravelGroups(travelGroup);
    }
}
