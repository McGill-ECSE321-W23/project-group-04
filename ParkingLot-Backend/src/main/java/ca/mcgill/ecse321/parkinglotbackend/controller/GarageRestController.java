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

/**
 * This class implements the controller class for Garage.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_exposing_service_functionality_via_a_restful_api
 */
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/garages")
public class GarageRestController {
    @Autowired
    private GarageService garageService;

    /**
     * RESTful API for the creation of a garage
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to create one.
     * @param garageNumber - The number (like a name) assigned to the garage
     * @return - Either a message specifying the user is not authorized to perform this, or the created garage if the user is authorized to perform this
     * @author Estefania Vazquez
     */
    @PostMapping("/create")
    ResponseEntity<?> createGarage(HttpServletRequest request, @RequestParam int garageNumber) {
        try {
            // If the user is the manager
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(garageService.createGarageService(garageNumber)));
            }

            // If the user is not the manager
            else {
                return ResponseEntity.badRequest().body("Only the manager can create a garage.");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * RESTful API for the deletion of  a garage.
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to delete one.
     * @param garageID - The unique ID of a garage
     * @return - Either a message specifying the user is not authorized to perform this, or the deleted garage if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API for the modification of a garage.
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to modify one.
     * @param garageID - The unique ID of a garage
     * @param garageNumber - The number (like a name) assigned to the garage
     * @return - Either a message specifying the user is not authorized to perform this, or the modified garage if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API to get a garage.
     *
     * @param request - Who is trying to access this method. Only staff are allowed to get one.
     * @param garageID - The unique ID of a garage
     * @return - Either a message specifying the user is not authorized to perform this, or the garage requested if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API get all garages.
     *
     * @param request - Who is trying to access this method. Only staff are allowed to get them.
     * @return - Either a message specifying the user is not authorized to perform this, or all the garages if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * Method to convert a garage into a garage data transfer object
     *
     * @param g - The garage
     * @return - The garage DTO
     * @author Estefania Vazquez
     */
    private GarageDto convertToDto(Garage g) {
        if (g == null) {
            throw new IllegalArgumentException("Garage does not exist.");
        }
        GarageDto garageDto = new GarageDto(g.getGarageID(), g.getGarageNumber());
        return garageDto;
    }
}
