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
public class StaffAccountController {

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

    @GetMapping("/schedule")
    ResponseEntity<?> getSchedule(HttpServletRequest request, @RequestParam Long staffAccountId) {
        try {
            if (AuthenticationUtility.isStaff(request) && AuthenticationUtility.getAccountId(request) == staffAccountId) {
                timeSlotService.getTimeSlotsByStaffAccountID(staffAccountId);
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().build();
    }

    @PutMapping("/modifySchedule")
    ResponseEntity<?> modifySchedule(HttpServletRequest request, @RequestParam long employeeId, @RequestBody List<TimeSlotDto> schedule) {
        try {
            if (AuthenticationUtility.isManager(request)) {
                List<TimeSlot> oldSchedule = timeSlotService.getTimeSlotsByStaffAccountID(employeeId);

                for (TimeSlot timeSlot: oldSchedule) {
                    timeSlotService.deleteTimeSlot(timeSlot.getTimeSlotID());
                }

                for (TimeSlotDto timeSlotDto: schedule) {
                    timeSlotService.createTimeSlot(
                            timeSlotDto.getDayOfTheWeek(),
                            timeSlotDto.getStartTime(),
                            timeSlotDto.getEndTime(),
                            parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1),
                            staffAccountService.getStaffAccount(employeeId)
                    );
                }
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();

        }
        return ResponseEntity.ok().build();
    }


    @PutMapping("/resign")
    ResponseEntity<?> fireEmployee(HttpServletRequest request, @RequestParam Long employeeId) throws Exception {
        try {
            if (AuthenticationUtility.isStaff(request)) {
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
    ResponseEntity<?> hireEmployee(HttpServletRequest request, @RequestBody StaffAccountDto staffAccountDto) {
        try {
            if (AuthenticationUtility.isManager(request)) {
                staffAccountService.createStaffAccount(
                        staffAccountDto.getPerson().getName(),
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

    @PutMapping("/manage")
    ResponseEntity<?> manageEmployee(HttpServletRequest request, @RequestBody StaffAccountDto staffAccountDto) {
        try {
            if (AuthenticationUtility.isManager(request)) {
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
