package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.service.GarageService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/garages")
public class GarageRestController {
    @Autowired
    private GarageService garageService;

    // Get a garage by id
    @GetMapping("get/{garageID}")
    ResponseEntity<?> getGarage(HttpServletRequest request, @PathVariable(value = "garageID") long garageID) {
        try {
            // If the user trying to get a garage is not staff
            if (AuthenticationUtility.isStaff(request)) { // Check which user is trying to call this method
                return ResponseEntity.ok(convertToDto(garageService.getGarageService(garageID)));
            }

            // If the user trying to get a garage is not staff
            else {
                return ResponseEntity.badRequest().body("Only staff can get a garage.");
            }
        } catch (Exception e) {
            // Return the error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all of the garages
    @GetMapping("/get")
    ResponseEntity<?> getAllGarages(HttpServletRequest request) {
        try {
            // If the user trying to get all the garages is staff
            if (AuthenticationUtility.isStaff(request)) { // Check which user is trying to call this method
                return ResponseEntity.ok(garageService.getAllGarageService().stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            }

            // If the user trying to get a garage is not the Manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can get all the garages.");
            }
        } catch (Exception e) {
            // Return the error
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create a garage
    @PostMapping("/create")
    ResponseEntity<?> createGarage(HttpServletRequest request, @RequestParam int garageNumber) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(garageService.deleteGarageService(garageNumber)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can create a garage.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Deleting a garage
    @PostMapping("/delete/{garageID}")
    ResponseEntity<?> deleteGarage(HttpServletRequest request, @PathVariable(value = "garageID") long garageID) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(garageService.deleteGarageService(garageID)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can delete a garage.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Modifying a garage
    @PostMapping("/modify/{garageID}")
    ResponseEntity<?> modifyGarage(HttpServletRequest request, @PathVariable(value = "garageID") long garageID,
                                   @RequestParam int garageNumber) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(garageService.modifyGarage(garageID, garageNumber)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can modify a garage.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private GarageDto convertToDto(Garage g) {
        if (g == null) {
            throw new IllegalArgumentException("Garage does not exist.");
        }
        GarageDto garageDto = new GarageDto(g.getGarageID());
        return garageDto;
    }
}
