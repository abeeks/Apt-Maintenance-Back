package com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.services;

import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceCreationException;
import com.abeeks.maintenancerequestserver.domain.core.exceptions.ResourceNotFoundException;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;

import java.util.List;
//This interface lists the methods that are implemented in the MaintenanceRequestController
public interface MaintenceRequestService {
    MaintenceRequest create(MaintenceRequest maintenceRequest) throws ResourceCreationException;
    MaintenceRequest getById(Long id) throws ResourceNotFoundException;
    MaintenceRequest getByEmail(String email) throws ResourceNotFoundException;
    List<MaintenceRequest> getAll();
    MaintenceRequest update(Long id, MaintenceRequest maintenceRequestDetail)throws ResourceNotFoundException;
    void delete(Long id);
}
