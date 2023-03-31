package ca.mcgill.ecse321.parkinglotbackend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingLotSoftwareSystemDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import jakarta.servlet.http.HttpServletRequest;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/parkinglotsoftwaresystem")
public class ParkingLotSoftwareSystemRestController {
    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    /**
     * @param request - Who is trying to access this method. There is no limitation on who is able to get the system
     * @param parkingLotSoftwareSystemID ID of the system
     * @return Error message or the parking lot system
     * @author Qin Xuan Xu
     */
    @GetMapping("/get/{parkingLotSoftwareSystemID}")
    public ResponseEntity<?> getParkingLotSoftwareSystem(HttpServletRequest request, @PathVariable(value = "parkingLotSoftwareSystemID") long parkingLotSoftwareSystemID) {
        // Everyone can get system
        try {
            return ResponseEntity.ok(convertToDto(parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingLotSoftwareSystemID)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Create a new system
     * @param request - Who is trying to access this method. Only the manager is allowed to do so.
     * @param monthlyFee Monthly fee of a reservation
     * @param feePer15m Fee for public parking for 15 min
     * @param maxStay Maximum stay in a parking spot
     * @param numberOfRegularParkingSpots Max number of regular parking spots
     * @param numberOfLargeParkingSpots Max numbe ro flarge parking spots
     * @param numberOfMonthlyFloors Max number of floors that hold monthly reservations
     * @param numberOfMonthlySpotsPerFloor Number of parking spots reserved for monthly reservations per floor
     * @param numberOfGarages Number of garages
     * @return Error message or the Created system
     * @author Qin Xuan Xu
     */
    @PostMapping("/create")
    public ResponseEntity<?> createParkingLotSoftwareSystem(HttpServletRequest request, @RequestParam float monthlyFee, @RequestParam float feePer15m, @RequestParam int maxStay, @RequestParam int numberOfRegularParkingSpots, @RequestParam int numberOfLargeParkingSpots, @RequestParam int numberOfMonthlyFloors, @RequestParam int numberOfMonthlySpotsPerFloor, @RequestParam int numberOfGarages) {
        // check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(parkingLotSoftwareSystemService.createParkingLotSoftwareSystem(monthlyFee, feePer15m, maxStay, numberOfRegularParkingSpots, numberOfLargeParkingSpots, numberOfMonthlyFloors, numberOfMonthlySpotsPerFloor, numberOfGarages)));
            } else {
                return ResponseEntity.badRequest().body("Only manager can create a system");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Update a system
     * @param request - Who is trying to access this method. Only the manager is allowed to do so.
     * @param parkingLotSoftwareSystemID ID of system to be updates
     * @param monthlyFee Updated onthly fee of a reservation
     * @param feePer15m Updated Fee for public parking for 15 min
     * @param maxStay Updated Maximum stay in a parking spot
     * @param numberOfRegularParkingSpots Updated Max number of regular parking spots
     * @param numberOfLargeParkingSpots Updated Max number of large parking spots
     * @param numberOfMonthlyFloors Updated Max number of floors that hold monthly reservations
     * @param numberOfMonthlySpotsPerFloor UpdatedNumber of parking spots reserved for monthly reservations per floor
     * @param numberOfGarages Updated Number of g arages
     * @return Error message or the updated system
     * @author Qin Xuan Xu
     */
    @PutMapping("/update/{parkingLotSoftwareSystemID}")
    public ResponseEntity<?> updateParkingLotSoftwareSystem(HttpServletRequest request, @PathVariable(value = "parkingLotSoftwareSystemID") long parkingLotSoftwareSystemID, @RequestParam float monthlyFee, @RequestParam float feePer15m, @RequestParam int maxStay, @RequestParam int numberOfRegularParkingSpots, @RequestParam int numberOfLargeParkingSpots, @RequestParam int numberOfMonthlyFloors, @RequestParam int numberOfMonthlySpotsPerFloor, @RequestParam int numberOfGarages) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(parkingLotSoftwareSystemService.updateParkingLotSoftwareSystem(parkingLotSoftwareSystemID, monthlyFee, feePer15m, maxStay, numberOfRegularParkingSpots, numberOfLargeParkingSpots, numberOfMonthlyFloors, numberOfMonthlySpotsPerFloor, numberOfGarages)));
            } else {
                return ResponseEntity.badRequest().body("Only manager can update system");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Delete system (DEPRECATED)
     * @param request - Who is trying to access this method. Nobody is allowed to do so.
     * @param parkingLotSoftwareSystemID ID of the system you wish to be deleted
     * @return Error message
     * @author Qin Xuan Xu
     */
    @DeleteMapping("/delete/{parkingLotSoftwareSystemID}")
    public ResponseEntity<?> deleteParkingLotSoftwareSystem(HttpServletRequest request, @PathVariable(value = "parkingLotSoftwareSystemID") long parkingLotSoftwareSystemID) {
        // Not allowed
        try {
            return ResponseEntity.badRequest().body("Deleting a system is not allowed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Helper method to convert parking lot system into DTO
     * @param s Parking lot system to be converted into DTO
     * @return Parking lot system DTO
     * @author Qin Xuan Xu
     */
    private ParkingLotSoftwareSystemDto convertToDto(ParkingLotSoftwareSystem s) {
        if (s == null) {
            throw new IllegalArgumentException("System does not exist");
        }
        ParkingLotSoftwareSystemDto parkingLotSoftwareSystemDto = new ParkingLotSoftwareSystemDto(
                s.getParkingLotSoftwareSystemID(), s.getMonthlyFee(), s.getFeePer15m(), s.getMaxStay(),
                s.getNumberOfLargeParkingSpots(), s.getNumberOfLargeParkingSpots(), s.getNumberOfMonthlyFloors(),
                s.getNumberOfMonthlySpotsPerFloor(), s.getNumberOfGarages());
        return parkingLotSoftwareSystemDto;
    }
}
