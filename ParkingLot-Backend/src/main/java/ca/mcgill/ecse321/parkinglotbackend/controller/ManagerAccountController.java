package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.service.AccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.ManagerAccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.StaffAccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.TimeSlotService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/manager")
public class ManagerAccountController {

    @Autowired
    ManagerAccountService managerAccountService;

    @Autowired
    StaffAccountService staffAccountService;

    @Autowired
    AccountService accountService;

    @Autowired
    TimeSlotService timeSlotService;

    @GetMapping("/manager")
    ResponseEntity<?> getManager(HttpServletRequest request, @RequestParam Long StaffAccountId) {
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok().body(managerAccountService.getManagerAccount());
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }
}