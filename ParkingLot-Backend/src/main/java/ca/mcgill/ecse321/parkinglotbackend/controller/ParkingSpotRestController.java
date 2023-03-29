
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingSpotService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingSpotDto;
import org.springframework.web.bind.annotation.DeleteMapping;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class ParkingSpotRestController {
     @Autowired
     private ParkingSpotService parkingSpotService;
    @PutMapping("/create")
    public ResponseEntity<?> createParkingSpot (HttpServletRequest request,
                                                @RequestBody int floor, @RequestBody int number) {
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
     @PostMapping("/update/{id}")
     public ResponseEntity<?> updateParkingSpot(HttpServletRequest request, @RequestBody long id,
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
     @PostMapping("/delete/{id}")
     public ResponseEntity<?> deleteParkingSpot(HttpServletRequest request, @RequestBody long id) {
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
//
     @PostMapping("/get/{id}")
     public ResponseEntity<?> getParkingSpotByID(HttpServletRequest request, @RequestBody long id) {
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
             ParkingSpot p = parkingSpotService.findParkingSpotByID(id);
                 int floor = p.getFloor();
                 int number = p.getNumber();
                 ParkingSpotDto parkingSpotDto = new ParkingSpotDto(id, floor, number);
             return ResponseEntity.ok().body(parkingSpotDto);
         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
////
     @PostMapping("/getParkingSpots")
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
             List <ParkingSpot> parkingSpots = parkingSpotService.findAllParkingSpots();
             List <ParkingSpotDto> parkingSpotDtos = new ArrayList<>();
             for (ParkingSpot p: parkingSpots){
                 long id = p.getParkingSpotID();
                 int floor = p.getFloor();
                 int number = p.getNumber();
                 ParkingSpotDto parkingSpotDto = new ParkingSpotDto(id, floor, number);
                 parkingSpotDtos.add(parkingSpotDto);
             }
             return ResponseEntity.ok().body(parkingSpotDtos);

         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
     @PostMapping("/getCount")
     public ResponseEntity<?> getParkingSpotCount(HttpServletRequest request) {
         try {
             List <ParkingSpot> parkingSpots = parkingSpotService.findAllParkingSpots();
             int count = parkingSpots.size();
             return ResponseEntity.ok().body(count);

         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
//
//     private ParkingSpotDto convertToDto(ParkingSpot p) {
//         if (p == null) {
//             throw new IllegalArgumentException("There is no such Parking Spot!");
//         }
//
//         long id = p.getParkingSpotID();
//         int floor = p.getFloor();
//         int number = p.getNumber();
//         ParkingSpotDto parkingSpotDto = new ParkingSpotDto(id, floor, number);
//
//
//         return parkingSpotDto;
//     }
//     private ParkingSpot convertToDomainObject(ParkingSpotDto pDto) {
//         List<ParkingSpot> parkingSpots = parkingSpotService.findAllParkingSpots();
//         for (ParkingSpot parkingSpot : parkingSpots) {
//             if (parkingSpot.getParkingSpotID() ==  (pDto.getParkingSpotID())) {
//                 return parkingSpot;
//             }
//         }
//         return null;
//     }





}

