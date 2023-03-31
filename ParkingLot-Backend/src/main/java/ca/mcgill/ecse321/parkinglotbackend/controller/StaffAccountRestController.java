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

    /**
     * RESTful API for getting the employee account
     *
     * @param request - Who is trying to access this method. Only the staff/manager is allowed to get one.
     * @param employeeId - The unique ID of employee account
     * @return - Either a message specifying the user is not authorized to perform this, or the employee account if the user is authorized to perform this
     * @author Edwin
     */
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

    /**
     * RESTful API for getting the schedule of a staff member
     *
     * @param request - Who is trying to access this method. Only the manager/staff is allowed to get one.
     * @param staffAccountId - The unique ID of staff account
     * @return - Either a message specifying the user is not authorized to perform this, or the schedule if the user is authorized to perform this
     * @author Edwin
     */
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

    /**
     * RESTful API for modifying the schedule of a staff member
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to get one.
     * @param employeeId - The unique ID of staff account whose schedule is being modified
     * @return - Either a message specifying the user is not authorized to perform this, or the schedule if the user is authorized to perform this
     * @author Edwin
     */
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

    /**
     * RESTful API for firing a staff member
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to do so.
     * @param employeeId  - The unique ID of staff account to be fired
     * @return - Either a message specifying the user is not authorized to perform this, or the staff account being fired if the user is authorized to perform this
     * @author Edwin
     */
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

    /**
     * RESTful API for hiring a new staff member
     *
     * @param request - Who is trying to access this method. Only the manager is allowed to do so.
     * @param name - employee name
     * @param email - employee email
     * @param password - employee password
     * @param phone  - employee phone number
     * @param salary - employee salary
     * @return - Either a message specifying the user is not authorized to perform this, or the schedule if the user is authorized to perform this
     * @author Edwin
     */
    @PostMapping("/hire")
    ResponseEntity<?> hireEmployee(HttpServletRequest request, @RequestParam String name, @RequestParam String phone,
                                   @RequestParam String password, @RequestParam String email, @RequestParam float salary) {
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok().body(DtoUtility.convertToDto(staffAccountService.createStaffAccount(
                        name,
                        phone,
                        password,
                        email,
                        salary)));
            } else {
                return ResponseEntity.badRequest().build();
            }
        } catch (Exception ex) {
            return ResponseEntity.internalServerError().build();
        }
    }

    /**
     * update an employee information
     * @param request - only the manager can access this method
     * @param staffAccountDto - the TO of the staff account object
     * @return  a message specifying the user is not authorized to perform this, otherwise success message
     * @author Edwin
     */
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
