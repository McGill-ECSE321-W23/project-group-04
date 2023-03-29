package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.MonthlyReservationDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.service.PersonService;
import ca.mcgill.ecse321.parkinglotbackend.service.MonthlyReservationService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/monthlyReservation")
public class MonthlyReservationController {

    @Autowired
    PersonService personService;

    @Autowired
    MonthlyReservationService monthlyReservationService;

    @PostMapping("/create")
    ResponseEntity<?> createReservation(HttpServletRequest request, @RequestBody MonthlyReservationDto monthlyReservationDto) {
        try {
            if (AuthenticationUtility.isLoggedIn(request)) {
                Person person = personService.getPersonByID(monthlyReservationDto.getPersonId());

                monthlyReservationService.addReservation(monthlyReservationDto.getStartDate(), monthlyReservationDto.getEndDate(), person);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @GetMapping("/vacant")
    ResponseEntity<?> getReservations(HttpServletRequest request, @RequestParam @Nullable long accountId) {
        try {
            if (AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.ok().body(monthlyReservationService.getAllReservations());
            } else if (AuthenticationUtility.isLoggedIn(request)) {
                return ResponseEntity.ok().body(monthlyReservationService.getCustomerReservations(accountId));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
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
}
