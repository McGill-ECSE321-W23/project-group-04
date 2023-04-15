package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * This class implements the controller class for OfferedService.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_exposing_service_functionality_via_a_restful_api
 */
//@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/offeredServices")
public class OfferedServiceRestController {
    @Autowired
    private OfferedServiceService offeredServiceService;

    /**
     * RESTful API for the creation of an offered service.
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to create one.
     * @param description - A description of the service offered (like a name)
     * @param cost - The cost of the offered service
     * @param duration - The duration of the offered service
     * @return - Either a message specifying the user is not authorized to perform this, or the created offered service if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API for the deletion of an offered service.
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to delete one.
     * @param serviceID - The unique ID of an offered service
     * @return - Either a message specifying the user is not authorized to perform this, or the deleted offered service if the user is authorized to perform this
     * @author Estefania Vazquez
     */
    @PostMapping("/delete/{offeredServiceID}")
    ResponseEntity<?> deleteOfferedService(HttpServletRequest request, @PathVariable(value = "offeredServiceID") long serviceID) {
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

    /**
     * RESTful API for the modification of an offered service.
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to modify one.
     * @param offeredServiceID - The unique ID of an offered service
     * @param description - A description of the service offered (like a name)
     * @param cost - The cost of the offered service
     * @param duration - The duration of the offered service
     * @return - Either a message specifying the user is not authorized to perform this, or the modified offered service if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API to get an offered service.
     *
     * @param request - Who is trying to access this method. Only staff are allowed to get one.
     * @param offeredServiceID - The unique ID of an offered service
     * @return - Either a message specifying the user is not authorized to perform this, or the offered service requested if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * RESTful API get all offered services.
     *
     * @param request - Who is trying to access this method. Only staff are allowed to get them.
     * @return - Either a message specifying the user is not authorized to perform this, or all the offered services if the user is authorized to perform this
     * @author Estefania Vazquez
     */
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

    /**
     * Method to convert an offered service into an offered service data transfer object
     *
     * @param os - The offered service
     * @return - The offered service DTO
     * @author Estefania Vazquez
     */
    private OfferedServiceDto convertToDto(OfferedService os) {
        if (os == null) {
            throw new IllegalArgumentException("Service does not exist.");
        }
        OfferedServiceDto offeredServiceDto = new OfferedServiceDto(os.getServiceID(), os.getDescription(), os.getCost(), os.getDuration());
        return offeredServiceDto;
    }
}
