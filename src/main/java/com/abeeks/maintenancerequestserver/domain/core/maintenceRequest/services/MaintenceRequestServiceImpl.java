package com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.services;

import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.repos.MaintenceRequestRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaintenceRequestServiceImpl implements MaintenceRequestService{

    private MaintenceRequestRepo maintenceRequestRepo;
    @Autowired
    public MaintenceRequestServiceImpl(MaintenceRequestRepo maintenceRequestRepo){
        this.maintenceRequestRepo = maintenceRequestRepo;
    }
    @Override
    public MaintenceRequest create(MaintenceRequest maintenceRequest) throws ResourceCreationException {
        Optional<MaintenceRequest> optional = maintenceRequestRepo.findByEmail(maintenceRequest.getEmail());
        if(optional.isPresent())
            throw new ResourceCreationException("Maintenance Request with email exists: " + maintenceRequest.getEmail());
        maintenceRequest = maintenceRequestRepo.save(maintenceRequest);
        return maintenceRequest;
    }

    @Override
    public MaintenceRequest getById(Long id) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = maintenceRequestRepo.findById(id)
                .orElseThrow(()->new ResourceNotFoundException("No Maintenance Request with id: " + id));
        return maintenceRequest;
    }

    @Override
    public MaintenceRequest getByEmail(String email) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = maintenceRequestRepo.findByEmail(email)
                .orElseThrow(()->new ResourceNotFoundException("No Employee with email: " + email));
        return maintenceRequest;
    }

    @Override
    public List<MaintenceRequest> getAll() {
        return maintenceRequestRepo.findAll();
    }

    @Override
    public MaintenceRequest update(Long id, MaintenceRequest maintenceRequestDetail) throws ResourceNotFoundException {
        MaintenceRequest maintenceRequest = getById(id);
        maintenceRequest.setFirstName(maintenceRequestDetail.getFirstName());
        maintenceRequest.setLastName(maintenceRequestDetail.getLastName());
        maintenceRequest.setEmail(maintenceRequestDetail.getEmail());
        maintenceRequest = maintenceRequestRepo.save(maintenceRequest);
        return maintenceRequest;
    }

    @Override
    public void delete(Long id) {
        MaintenceRequest maintenceRequest = getById(id);
        maintenceRequestRepo.delete(maintenceRequest);
    }
}
