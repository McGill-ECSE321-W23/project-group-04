
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingSpotDto;
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

    @PostMapping(value = {"/create", "/create/" })
    public ResponseEntity<?> createParkingSpot (HttpServletRequest request, @RequestBody int floor, @RequestBody int number) {
        // Check authorization (manager)
        try {
            if (!AuthenticationUtility.isManager(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            parkingSpotService.createParkingSpot(floor, number);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PutMapping(value = {"/update/{id}", "/update/{id}/" })
    public ResponseEntity<?> updateParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id,
                                               @RequestBody int newFloor, @RequestBody int newNumber) {
        // Check authorization (manager)
        try {
            if (!AuthenticationUtility.isManager(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            parkingSpotService.updateParkingSpot(id, newFloor, newNumber);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ResponseEntity<?> deleteParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (manager)
        try {
            if (!AuthenticationUtility.isManager(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            parkingSpotService.deleteParkingSpot(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = {"/get/{id}", "/get/{id}/"})
    public ResponseEntity<?> getParkingSpotByID(HttpServletRequest request, @PathVariable(value = "id") long id) {
        // Check authorization (own a/c or staff)
        try {
            long accountId = AuthenticationUtility.getAccountId(request);
            if (accountId != id && !AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        try {
            ParkingSpot parkingSpot = parkingSpotService.findParkingSpotByID(id);

            return ResponseEntity.ok().body(convertToDto(parkingSpot));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping(value = {"/getParkingSpots", "/getParkingSpots/"})
    public ResponseEntity<?> getAllParkingSpots(HttpServletRequest request) {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        try {
            List <ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots();
            List <ParkingSpotDto> parkingSpotDtos = new ArrayList<>();
            for (ParkingSpot parkingSpot: parkingSpots){
                parkingSpotDtos.add(convertToDto(parkingSpot));
            }
            return ResponseEntity.ok().body(parkingSpotDtos);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value = {"/getCount", "/getCount/"})
    public ResponseEntity<?> getParkingSpotCount(HttpServletRequest request) {
        try {
            List <ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots();
            int count = parkingSpots.size();
            return ResponseEntity.ok().body(count);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private ParkingSpotDto convertToDto(ParkingSpot p) {
        if (p == null) {
            throw new IllegalArgumentException("There is no such Parking Spot!");
        }

        long id = p.getParkingSpotID();
        int floor = p.getFloor();
        int number = p.getNumber();
        ParkingSpotDto parkingSpotDto = new ParkingSpotDto(id, floor, number);


        return parkingSpotDto;
    }
    private ParkingSpot convertToDomainObject(ParkingSpotDto pDto) {
        List<ParkingSpot> parkingSpots = parkingSpotService.getAllParkingSpots();
        for (ParkingSpot parkingSpot : parkingSpots) {
            if (parkingSpot.getParkingSpotID() ==  (pDto.getParkingSpotID())) {
                return parkingSpot;
            }
        }
        return null;
    }





}


