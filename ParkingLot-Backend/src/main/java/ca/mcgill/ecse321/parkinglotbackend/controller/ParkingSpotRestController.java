
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingSpotService;
import ca.mcgill.ecse321.parkinglotbackend.service.TicketService;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class ParkingSpotRestController {
    @Autowired
    private ParkingSpotService parkingSpotService;

    @PostMapping("/view/{id}")
    public ResponseEntity<?> viewParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id) {
        try {
            ParkingSpot parkingSpot = parkingSpotService.findParkingSpotByID(id);
            int floor = parkingSpot.getFloor();
            int number = parkingSpot.getNumber();
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping(value = {"/createParkingSpot", "/createParkingSpot/" })
    public ResponseEntity<?> createParkingSpot (HttpServletRequest request, @RequestBody int floor
            , @RequestBody int number) {
        try {
            parkingSpotService.createParkingSpot(floor, number);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id,
                                           @RequestBody int floor, @RequestBody int number) {
        try {
            parkingSpotService.updateParkingSpot(id, floor, number);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id) {
        try {
            parkingSpotService.deleteParkingSpot(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




}


