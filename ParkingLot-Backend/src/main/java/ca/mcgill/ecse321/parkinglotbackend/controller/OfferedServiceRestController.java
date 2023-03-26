package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/offeredServices")
public class OfferedServiceRestController {
    @Autowired
    private OfferedServiceService offeredServiceService;

    // Get an offered service by id
    @GetMapping("/get/{offeredServiceID}")
    ResponseEntity<?> getOfferedService(HttpServletRequest request, @PathVariable(value = "offeredServiceID") long offeredServiceID) {
        try {
            // If the user trying to get a service is not staff
            if (AuthenticationUtility.isStaff(request)) { // Check which user is trying to call this method
                return ResponseEntity.ok(convertToDto(offeredServiceService.getOfferedServiceService(offeredServiceID)));
            }

            // If the user trying to get a service is not staff
            else {
                return ResponseEntity.badRequest().body("Only staff can get a service.");
            }
        } catch (Exception e) {
            // Return the error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all of the offered services
    @GetMapping("/get")
    ResponseEntity<?> getAllOfferedServices(HttpServletRequest request) {
        try {
            // If the user trying to get all services is not staff
            if (AuthenticationUtility.isStaff(request)) { // Check which user is trying to call this method
                return ResponseEntity.ok(offeredServiceService.getAllOfferedServiceService().stream().map(os -> convertToDto(os)).collect(Collectors.toList()));
            }

            // If the user trying to get all services is not staff
            else {
                return ResponseEntity.badRequest().body("Only staff can get all services offered.");
            }
        } catch (Exception e) {
            // Return the error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Creating an offered service
    @PostMapping("/create")
    ResponseEntity<?> createOfferedService(HttpServletRequest request, @RequestParam String description,
                                           @RequestParam float cost, @RequestParam int duration) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(offeredServiceService.createOfferedServiceService(description, cost, duration)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can create a service offered.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deleting an offered service
    @PostMapping("/delete/{offeredServiceID}")
    ResponseEntity<?> deleteOfferedService(HttpServletRequest request, @PathVariable(value = "serviceID") long serviceID) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(offeredServiceService.deleteOfferedServiceService(serviceID)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can delete a service offered.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Modifying an offered service
    @PostMapping("/modify/{offeredServiceID}")
    ResponseEntity<?> modifyOfferedService(HttpServletRequest request, @PathVariable(value = "offeredServiceID") long offeredServiceID,
                                   @RequestParam String description, @RequestParam float cost, @RequestParam int duration) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(offeredServiceService.modifyOfferedServiceService(offeredServiceID, description, cost, duration)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can modify a service offered.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private OfferedServiceDto convertToDto(OfferedService os) {
        if (os == null) {
            throw new IllegalArgumentException("Service does not exist.");
        }
        OfferedServiceDto offeredServiceDto = new OfferedServiceDto(os.getServiceID());
        return offeredServiceDto;
    }
}
