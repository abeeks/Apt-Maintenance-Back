package com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.services;

import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.repos.MaintenceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service//@Service annotation is often used to annotate classes that provide business services, perform business logic, or coordinate transactions.
public class MaintenceRequestServiceImpl implements MaintenceRequestService{

    private MaintenceRequestRepo maintenceRequestRepo;
    @Autowired
    public MaintenceRequestServiceImpl(MaintenceRequestRepo maintenceRequestRepo){
        this.maintenceRequestRepo = maintenceRequestRepo;
    }
    @Override//this covers a situation if there is an error in the createMaintenanceRequest object. It will reject the request if an email is associated with an
    // existing maintenance request
    public MaintenceRequest create(MaintenceRequest maintenceRequest) throws ResourceCreationException {
        Optional<MaintenceRequest> optional = maintenceRequestRepo.findByEmail(maintenceRequest.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Maintenance Request with email exists: " + maintenceRequest.getEmail());
        maintenceRequest = maintenceRequestRepo.save(maintenceRequest);
        return maintenceRequest;
    }

    @Override//this method allows us to search for a request by the ID, and if no ID is found, it returns the ResourceNotFoundException
    public MaintenceRequest getById(Long id) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = maintenceRequestRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with id: " + id));
        return maintenceRequest;
    }

    @Override//this method allows us to search for a request by the email, and if no email is found, it returns the ResourceNotFoundException
    public MaintenceRequest getByEmail(String email) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = maintenceRequestRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No Employee with email: " + email));
        return maintenceRequest;
    }

    @Override  //this method allows us to list all the maintenance requests
    public List<MaintenceRequest> getAll() {
        return maintenceRequestRepo.findAll();
    }

    @Override//this method allows us to update an existing request
    public MaintenceRequest update(Long id, MaintenceRequest maintenceRequestDetail) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = getById(id);
        maintenceRequest.setFirstName(maintenceRequestDetail.getFirstName());
        maintenceRequest.setLastName(maintenceRequestDetail.getLastName());
        maintenceRequest.setEmail(maintenceRequestDetail.getEmail());
        maintenceRequest = maintenceRequestRepo.save(maintenceRequest);
        return maintenceRequest;
    }

    @Override//this method allows us to delete an existing request
    public void delete(Long id) {
        MaintenceRequest maintenceRequest = getById(id);
        maintenceRequestRepo.delete(maintenceRequest);
    }
}
