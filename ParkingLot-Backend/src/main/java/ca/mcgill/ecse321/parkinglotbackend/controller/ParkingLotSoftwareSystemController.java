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
public class ParkingLotSoftwareSystemController {
    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    // Get system by id
    @GetMapping("/get/{parkingLotSoftwareSystemID}")
    public ResponseEntity<?> getParkingLotSoftwareSystem(HttpServletRequest request, @PathVariable(value = "parkingLotSoftwareSystemID") long parkingLotSoftwareSystemID) {
        // Everyone can get system
        try {
            return ResponseEntity.ok(convertToDto(parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingLotSoftwareSystemID)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create system
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

    // Update system
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

    // Delete system
    @DeleteMapping("/delete/{parkingLotSoftwareSystemID}")
    public ResponseEntity<?> deleteParkingLotSoftwareSystem(HttpServletRequest request, @PathVariable(value = "parkingLotSoftwareSystemID") long parkingLotSoftwareSystemID) {
        // Check authorization
        try {
            return ResponseEntity.badRequest().body("Deleting a system is not allowed");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

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
