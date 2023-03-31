package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.DtoUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.StaffAccountDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.TimeSlotDto;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/staff")
public class StaffAccountRestController {

    @Autowired
    StaffAccountService staffAccountService;

    @Autowired
    AccountService accountService;

    @Autowired
    MonthlyReservationService monthlyReservationService;

    @Autowired
    TimeSlotService timeSlotService;

    @Autowired
    ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    @GetMapping("/{employeeId}")
    ResponseEntity<?> getEmployee(HttpServletRequest request, @PathVariable Long employeeId) {
        try {
            if ((AuthenticationUtility.isManager(request) || (AuthenticationUtility.isStaff(request) && AuthenticationUtility.getAccountId(request) == employeeId)) && employeeId != null) {
                return ResponseEntity.ok().body(DtoUtility.convertToDto(staffAccountService.getStaffAccount(employeeId)));
            } else {
                return ResponseEntity.badRequest().body("Unauthorized");
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        
    }

    @GetMapping("/schedule")
    ResponseEntity<?> getSchedule(HttpServletRequest request, @RequestParam Long staffAccountId) {
        try {
            if (AuthenticationUtility.isManager(request) || (AuthenticationUtility.isStaff(request) && AuthenticationUtility.getAccountId(request) == staffAccountId)) {
                return ResponseEntity.ok().body(timeSlotService.getTimeSlotsByStaffAccountID(staffAccountId));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/modifySchedule")
    ResponseEntity<?> modifySchedule(HttpServletRequest request, @RequestParam Long employeeId, @RequestBody List<TimeSlotDto> schedule) {
        try {
            if (AuthenticationUtility.isManager(request) && employeeId != null) {
                List<TimeSlot> oldSchedule = timeSlotService.getTimeSlotsByStaffAccountID(employeeId);

                for (TimeSlot timeSlot: oldSchedule) {
                    timeSlotService.deleteTimeSlot(timeSlot.getTimeSlotID());
                }

                for (TimeSlotDto timeSlotDto: schedule) {
                    timeSlotService.createTimeSlot(
                        timeSlotDto.getDayOfTheWeek(),
                        timeSlotDto.getStartTime(),
                        timeSlotDto.getEndTime(),
                        null,
                        staffAccountService.getStaffAccount(employeeId)
                    );
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();

        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/fire")
    ResponseEntity<?> fireEmployee(HttpServletRequest request, @RequestParam Long employeeId) throws Exception {
        try {
            if (AuthenticationUtility.isManager(request)) {
                staffAccountService.deleteStaffAccount(employeeId);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping("/hire")
    ResponseEntity<?> hireEmployee(HttpServletRequest request, @RequestParam String name, @RequestParam String phone, @RequestParam String password, @RequestParam String email, @RequestParam float salary) {
        try {
            if (AuthenticationUtility.isManager(request)) {
                staffAccountService.createStaffAccount(
                        name,
                        phone,
                        password,
                        email,
                        salary
                );
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update")
    ResponseEntity<?> updateEmployee(HttpServletRequest request, @RequestBody StaffAccountDto staffAccountDto) {
        try {
            if (AuthenticationUtility.isManager(request) && StaffAccountDto.isValid(staffAccountDto)) {
                staffAccountService.updateStaffAccount(
                        staffAccountDto.getAccountID(),
                        staffAccountDto.getEmail(),
                        staffAccountDto.getPassword(),
                        DtoUtility.convertToEntity(staffAccountDto.getPerson()),
                        staffAccountDto.getSalary()
                );
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }
}
