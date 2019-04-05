package com.travelagency.controllers;

import com.travelagency.repository.ServiceRepository;
import org.springframework.stereotype.Service;

@Service
public class ServiceController {

    private ServiceRepository serviceRepository;

    public ServiceController(ServiceRepository serviceRepository) { this.serviceRepository = serviceRepository; }

    public com.travelagency.model.Service createService(com.travelagency.model.Service service) {
        if(service.getId() != 0){
            return null;
        }
        return this.serviceRepository.save(service);
    }

    public com.travelagency.model.Service getById(long id) { return this.serviceRepository.getOne(id); }

    public com.travelagency.model.Service updateService(com.travelagency.model.Service updatedService) {
        if(!this.serviceRepository.existsById(updatedService.getId())){
            return null;
        }
        return this.serviceRepository.save(updatedService);
    }

    public boolean deleteService(long id) {
        boolean doesExist = this.serviceRepository.existsById(id);
        if(!doesExist){
            return false;
        }
        this.serviceRepository.deleteById(id);
        return true;
    }
}
