package com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.repos;

import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
//The Optional class is a container object that may or may not contain a non-null value.
// It is commonly used to avoid null pointer exceptions and to represent the possibility of no value being present.

public interface MaintenceRequestRepo extends JpaRepository<MaintenceRequest, Long> {
    //The JpaRepository interface provides CRUD (Create, Read, Update, Delete) operations, as well as additional methods for working with entities in a database.
// It takes in T (type) and is a placeholder that is replaced by an actual type when the generic class or method is used, and it takes the ID.
    Optional<MaintenceRequest> findByEmail(String email);
    //findAll retrieves all the records of an entity type without having to write a custom query for this common use case. The Spring Data JPA framework
    // generates the appropriate SQL query based on the entity type and ID type provided in the repository declaration.
}
