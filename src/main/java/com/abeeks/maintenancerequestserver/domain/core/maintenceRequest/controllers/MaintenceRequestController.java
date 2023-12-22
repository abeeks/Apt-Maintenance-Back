package com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.controllers;

import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.models.MaintenceRequest;
import com.abeeks.maintenancerequestserver.domain.core.maintenceRequest.services.MaintenceRequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//this whole file is our CRUD method.
@RestController //@RestController is a specialized version of controller, used to define RESTful web services. Combines @Controller and @ResponseBody
@CrossOrigin //@CrosOrigin supports secure CrossOrigin requests and data transfers between browsers and servers
@RequestMapping("/api/v1/maintenance") //@RequestMapping -- Maps HTTP requests to handler methods in a controller.
//Can be applied at the class level and/or method level. It has various attributes to match by URL, HTTP method, request parameters, headers, and media types
public class MaintenceRequestController {
    //used to utilize the interface MaintenanceRequestService so we can use the methods in that file
    private MaintenceRequestService maintenceRequestService;
    @Autowired //@Autowired -- Marks a constructor, field, or setter method to automatically inject dependencies. Reduces the need for explicit bean wiring.
    public MaintenceRequestController(MaintenceRequestService maintenceRequestService) {
        this.maintenceRequestService = maintenceRequestService;
    }
    @GetMapping //@GetMapping -- maps the HTTP GET request to specific handler methods in a controller.
    public ResponseEntity<List<MaintenceRequest>> getAll(){  //to set the body, status, and headers of an HTTP response using ResponseEntity
        List<MaintenceRequest> maintenceRequest = maintenceRequestService.getAll();
        return new ResponseEntity<>(maintenceRequest, HttpStatus.OK); //This line of code is telling the computer to send back a response saying that everything is okay
        // and include a list of maintenance requests.
    }
    @PostMapping //@PostMapping -- maps the HTTP POST request to specific handler methods in a controller.
    public  ResponseEntity<MaintenceRequest> create(@RequestBody MaintenceRequest maintenceRequest){ //@RequestBody maps the HTTP REQUEST body to a transfer or domain object, enabling automatic deserialization
        // (which is the process of turning it from computer language to human-readable language)
        maintenceRequest = maintenceRequestService.create(maintenceRequest);
        return new ResponseEntity<>(maintenceRequest, HttpStatus.CREATED);//HttpStatus.CREATED is a 201 code that indicates the server was successfully processed the request, the new resource has been created and is now ready for interaction
    }
    @GetMapping("{id}")//@GetMapping("{id}") indicates that the annotated method should handle GET requests where the URL path includes a variable placeholder {id}.
    // This variable placeholder is typically a path variable, and the value specified in the URL will be passed to the corresponding method parameter.
    public ResponseEntity<MaintenceRequest> getById(@PathVariable("id") Long id){//the @PathVariable annotation can be used to handle template variables in the request URI mapping, and set them as method parameters.
        MaintenceRequest maintenceRequest = maintenceRequestService.getById(id);
        return new ResponseEntity<>(maintenceRequest, HttpStatus.OK);
    }
    @GetMapping("lookup") //lookup is used to find email, not dynamic because this word does not change. You would type ?email= the email address to use this method.
    public ResponseEntity<MaintenceRequest> getByEmail(@RequestParam String email){
        MaintenceRequest maintenceRequest = maintenceRequestService.getByEmail(email);
        return new ResponseEntity<>(maintenceRequest, HttpStatus.OK);
    }
    @PutMapping("{id}") // The PUT method is used to update a resource or create a new resource if it doesn't exist at a specified URI (Uniform Resource Identifier).
    public ResponseEntity<MaintenceRequest> update(@PathVariable("id") Long id, @RequestBody MaintenceRequest maintenceRequestDetail){
        maintenceRequestDetail = maintenceRequestService.update(id, maintenceRequestDetail);
        return new ResponseEntity<>(maintenceRequestDetail, HttpStatus.ACCEPTED);//HttpStatus.ACCEPTED represents the HTTP status code 202 Accepted
    }
    @DeleteMapping("{id}") //@DeleteMapping annotation is used to map HTTP DELETE requests to a specific handler method. The DELETE method is
    // used to request the removal of a resource identified by a given URI.
    public ResponseEntity delete(@PathVariable("id") Long id){
        maintenceRequestService.delete(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);//HttpStatus.NO_CONTENT represents the HTTP status code 204 No Content
    }
}
