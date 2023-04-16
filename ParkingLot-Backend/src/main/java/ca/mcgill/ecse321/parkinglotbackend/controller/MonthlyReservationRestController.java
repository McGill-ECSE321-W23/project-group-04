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

import java.time.LocalDate;
@RestController
@RequestMapping("/api/monthlyReservation")
public class MonthlyReservationRestController {

    @Autowired
    PersonService personService;

    @Autowired
    MonthlyReservationService monthlyReservationService;

    @Autowired
    ParkingSpotService parkingSpotService;

    /**
     * RESTful API for the creation of a reservation
     *
     * @param request - Who is trying to access this method. Must be logged in to account to make a reservation
     * @param monthlyReservationDto - The parameters required for a monthly reservation (start and end date)
     * @return - Either am error message r the monthly reservation
     * @author Edwin
     */
    @PostMapping("/create")
    ResponseEntity<?> createReservation(HttpServletRequest request, @RequestParam LocalDate startDate, @RequestParam 
    LocalDate endDate, @RequestParam long personId) {
        try {
            if (AuthenticationUtility.isLoggedIn(request)) {
                MonthlyReservationDto monthlyReservationDto = new MonthlyReservationDto(startDate, endDate, personId);
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

    /**
     * RESTful API for getting the reservations of a person
     * @param request  Who is trying to access this method. Must be a staff member to access this information
     * @param accountId ID of the account we are retrievng the reservations
     * @return Error message or list of monthly reservations for person
     * @author Edwin
     */
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

    /**
     * RESTful API for getting the all reservations
     * @param request  Who is trying to access this method. Must be a staff member to access this information
     * @return Error message or list of all monthly reservations
     * @author Edwin
     */
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
    /**
     * RESTful API for cancelling a reservations
     * @param request  Who is trying to access this method. Must be a staff member to perform this task
     * @return Error message or the cancelled reservation
     * @author Edwin
     */
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

    /**
     * RESTful API for paying for a monthly reservation
     * @param request  Who is trying to access this method. Must be logged into your account
     * @param reservationId ID of the reservation to be paid for
     * @param amount Amount paid
     * @return Error message or monthly reservation that is paid for
     * @author Edwin
     */
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

    /**
     * RESTful API to update the location of a parking spot
     * @param request Who is trying to access this method. Must be staff account
     * @param reservationIdToUpdate The ID of the monthly reservation linked to a parking spot that we are trying to update
     * @param parkingSpotDto Parameters of the new updated parking spot
     * @return An error messahe or the monthly reservation DTO
     */
    @PutMapping("/updateLocation")
    ResponseEntity<?> updateParkingLocation(HttpServletRequest request, @RequestParam Long reservationIdToUpdate,
     @RequestBody ParkingSpotDto parkingSpotDto) {
       
        try {
            if (AuthenticationUtility.isStaff(request) && reservationIdToUpdate != null 
             && ParkingSpotDto.isValid(parkingSpotDto)
            ) {
               
                MonthlyReservation monthlyReservation = monthlyReservationService.getReservationById(reservationIdToUpdate).get();
                ParkingSpot parkingSpot = parkingSpotService.findParkingSpotByID(parkingSpotDto.getParkingSpotID());

                if (parkingSpot.getMonthlyReservation() != null) {
                    return ResponseEntity.badRequest().body("spot is already taken");
                }

                ParkingSpot oldSpot = parkingSpotService.getParkingSpotByReservationId(monthlyReservation.getMonthlyReservationID());
                parkingSpotService.unbind(oldSpot.getParkingSpotID());
                
                parkingSpotService.attachReservation(parkingSpot.getParkingSpotID(), monthlyReservation);

                return ResponseEntity.ok().build();
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}
