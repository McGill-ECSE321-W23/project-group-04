
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingSpotDto;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingSpotService;
import ca.mcgill.ecse321.parkinglotbackend.service.MonthlyReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("/api/parkingSpot")
@RestController
public class ParkingSpotRestController {

     @Autowired
     private ParkingSpotService parkingSpotService;
    private MonthlyReservationService monthlyReservationService;

    /**
     * Create a parking spot
     * @param request - only manager can call this method
     * @param floor - floor of the parking spot
     * @param number - number of the parking spot
     * @return success/error message
     */
    @PostMapping("/create")
    public ResponseEntity<?> createParkingSpot (HttpServletRequest request,
                                                @RequestParam int floor, @RequestParam int number) {
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

    /**
     * update an existing parking spot
     * @param request - only manager can call this method
     * @param id - parking spot id
     * @param newFloor - new floor
     * @param newNumber - new number
     * @return success/error message
     * @author faizachowdhury
     */
     @PutMapping("/update/{id}")
     public ResponseEntity<?> updateParkingSpot(HttpServletRequest request, @PathVariable(value = "id") long id,
                                                @RequestParam int newFloor, @RequestParam int newNumber) {
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

    /**
     * delete a parking spot
     * @param request only manager can call this method
     * @param id parking spot id
     * @return success/error message
     * @author faizachowdhury
     */
     @DeleteMapping("/delete/{id}")
     public ResponseEntity<?> deleteParkingSpot(HttpServletRequest request,  @PathVariable(value = "id") long id) {
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

    /**
     * get a parking spot by ID
     * @param request - only staff or customer getting info for their own id can call this method
     * @param id - parking spot id
     * @return  the parking spot information
     * @author faizachowdhury
     */
     @GetMapping("/get/{id}")
     public ResponseEntity<?> getParkingSpotByID(HttpServletRequest request,@PathVariable(value = "id") long id) {
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

    /**
     * Get a list of all the parking spots
     * @param request - only staff can call this method
     * @return a list of all parking spots
     * @author faizachowdhury
     */
     @GetMapping("/getAll")
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

    /**
     * Return the number of parking spots
     * @param request - anyone can call this method
     * @return  count if successful
     * @author faizachowdhury
     */
     @GetMapping("/getCount")
     public ResponseEntity<?> getParkingSpotCount(HttpServletRequest request) {
         try {
             List <ParkingSpot> parkingSpots = parkingSpotService.findAllParkingSpots();
             int count = parkingSpots.size();
             return ResponseEntity.ok().body(count);

         } catch (Exception e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
     /**
     * Return the PS linked to a reservation
     * @param request - anyone can call this method
     * @param reservationId - monthly res ID 
     * @return found spot
     * @author faizachowdhury
     */
    @GetMapping("/getByResId")
    public ResponseEntity<?> getParkingSpotByReservationId(HttpServletRequest request, @RequestParam long reservationId) {
        ParkingSpotDto parkingSpotDto = null;
        try {
            ParkingSpot parkingSpot = parkingSpotService.getParkingSpotByReservationId(reservationId);
            List <ParkingSpot> parkingSpots = parkingSpotService.findAllParkingSpots();
            for (ParkingSpot p: parkingSpots){
                if (p.getParkingSpotID() == parkingSpot.getParkingSpotID()) {
                long id = p.getParkingSpotID();
                int floor = p.getFloor();
                int number = p.getNumber();
                parkingSpotDto = new ParkingSpotDto(id, floor, number);
                break;
            }
                if (parkingSpotDto == null) {
                    return ResponseEntity.notFound().build();
                }
            
        }
        return ResponseEntity.ok().body(parkingSpotDto);
     } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    /**
     * Return the PS linked to a reservation
     * @param request - anyone can call this method
     * @param reservationId - monthly res ID 
     * @return found spot
     * @author faizachowdhury
     */
    @PutMapping("/attachReservation")
    public ResponseEntity<?> attachReservation(HttpServletRequest request, @RequestParam long reservationId, @RequestParam long parkingSpotId) {
        
        try {
            MonthlyReservation monthlyReservation = monthlyReservationService.getReservationById(reservationId).get();
            parkingSpotService.attachReservation(parkingSpotId, monthlyReservation);
            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();

    }
    /**
     * Return the PS linked to a reservation
     * @param request - anyone can call this method
     * @param reservationId - monthly res ID 
     * @return found spot
     * @author faizachowdhury
     */
    @PutMapping("/unbind")
    public ResponseEntity<?> unbind(HttpServletRequest request, @RequestParam long parkingSpotId) {
        
        try {
            parkingSpotService.unbind(parkingSpotId);

            
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
        return ResponseEntity.ok().build();

    }



}

