package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.DtoUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.MonthlyReservationDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingSpotDto;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.MonthlyReservationService;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingSpotService;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monthlyReservation")
public class MonthlyReservationController {

    @Autowired
    PersonService personService;

    @Autowired
    MonthlyReservationService monthlyReservationService;

    @Autowired
    ParkingSpotService parkingSpotService;

    @PostMapping("/create")
    ResponseEntity<?> createReservation(HttpServletRequest request, @RequestBody MonthlyReservationDto monthlyReservationDto) {
        try {
            if (AuthenticationUtility.isLoggedIn(request)) {
                Person person = personService.getPersonByID(monthlyReservationDto.getPersonId());

                MonthlyReservationDto res = DtoUtility.convertToDto(monthlyReservationService.addReservation(monthlyReservationDto.getStartDate(), monthlyReservationDto.getEndDate(), person));

            return ResponseEntity.ok().body(res);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{accountId}")
    ResponseEntity<?> getReservationsForPerson(HttpServletRequest request, @PathVariable Long accountId) {
        try {
            if (AuthenticationUtility.isStaff(request) ||
                (AuthenticationUtility.isLoggedIn(request) && AuthenticationUtility.getAccountId(request) == accountId)) {
                return ResponseEntity.ok().body(monthlyReservationService.getCustomerReservations(accountId));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/allReservations")
    ResponseEntity<?> getAllReservations(HttpServletRequest request) {
        try {
            if (AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.ok().body(monthlyReservationService.getAllReservations());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/cancel")
    ResponseEntity<?> cancelReservations(HttpServletRequest request, @RequestParam Long reservationId) {
        try {
            if (AuthenticationUtility.isStaff(request)) {
                monthlyReservationService.deleteReservation(reservationId);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/pay")
    ResponseEntity<?> pay(HttpServletRequest request, @RequestParam long reservationId, @RequestParam float amount) {
        try {
            if (AuthenticationUtility.isLoggedIn(request)) {
                return ResponseEntity.ok().body(monthlyReservationService.renewPayment(reservationId, amount));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/updateLocation")
    ResponseEntity<?> updateParkingLocation(HttpServletRequest request, @RequestParam Long reservationIdToUpdate , @RequestBody ParkingSpotDto parkingSpotDto) {
        try {
            if (AuthenticationUtility.isStaff(request) && reservationIdToUpdate != null && ParkingSpotDto.isValid(parkingSpotDto)) {
                MonthlyReservation monthlyReservation = monthlyReservationService.getReservationById(reservationIdToUpdate).get();

                ParkingSpot parkingSpot = parkingSpotService.getParkingSpot(parkingSpotDto.getParkingSpotID());

                if (parkingSpot.getMonthlyReservation() != null) {
                    parkingSpotService.attachReservation(parkingSpot.getParkingSpotID(), monthlyReservation);
                } else {
                    return ResponseEntity.badRequest().body("spot is already taken");
                }

                ParkingSpot oldSpot = parkingSpotService.getParkingSpotByReservationId(monthlyReservation.getMonthlyReservationID());
                parkingSpotService.unbind(oldSpot.getParkingSpotID());

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
