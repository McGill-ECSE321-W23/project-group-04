package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.TimeSlotDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import ca.mcgill.ecse321.parkinglotbackend.service.StaffAccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.TimeSlotService;
import jakarta.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/timeslot")
public class TimeSlotController {
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;
    @Autowired
    private StaffAccountService staffAccountService;

    // Get timeslot by id
    @GetMapping("/get/{timeSlotID}")
    public ResponseEntity<?> getTimeSlot(HttpServletRequest request, @PathVariable(value = "timeSlotID") long timeSlotID) {
        // Check authorization
        try {
            if (AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.ok(convertToDto(timeSlotService.getTimeSlot(timeSlotID)));
            } else {
                return ResponseEntity.badRequest().body("Only staff can get a TimeSlot");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all timeslots
    @GetMapping("/get")
    public ResponseEntity<?> getAllTimeSlots(HttpServletRequest request) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(timeSlotService.getAllTimeSlots().stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            } else {
                return ResponseEntity.badRequest().body("Only manager can get all TimeSlots");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all timeslots of one employee
    @GetMapping("/get/{accountID}")
    public ResponseEntity<?> getAllTimeSlotsByAccountID(HttpServletRequest request, @PathVariable(value = "accountID") long accountID) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(timeSlotService.getTimeSlotsByAccountID(accountID).stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            } else {
                return ResponseEntity.badRequest().body("Only manager can get all TimeSlots by accountID");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Get all timeslots as opening hours
    @GetMapping("/getopen}")
    public ResponseEntity<?> getAllTimeSlotsByStaffAccount(HttpServletRequest request, StaffAccount staffAccount) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(timeSlotService.getTimeSlotsByStaffAccount(staffAccount).stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            } else {
                return ResponseEntity.badRequest().body("Only manager can get opening hours");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Create timeslot
    @PostMapping("/create")
    public ResponseEntity<?> createTimeSlot(HttpServletRequest request, @RequestBody DayOfWeek dayOfTheWeek, @RequestBody LocalTime startTime, @RequestBody LocalTime endTime, @RequestBody long parkingLotSoftwareSystemID, @RequestBody long accountID) {
        ParkingLotSoftwareSystem system = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingLotSoftwareSystemID);
        StaffAccount staffAccount = staffAccountService.getStaffAccount(accountID);
        if ((staffAccount == null && system == null) || (staffAccount != null && system != null)) {
            return ResponseEntity.badRequest().body("Cannot create timeslot");
        }

        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                if (system == null) {
                    return ResponseEntity.ok(convertToDto(timeSlotService.createTimeSlot(dayOfTheWeek, startTime, endTime, null, staffAccount)));
                } else {
                    return ResponseEntity.ok(convertToDto(timeSlotService.createTimeSlot(dayOfTheWeek, startTime, endTime, system, null)));
                }
            } else {
                return ResponseEntity.badRequest().body("Only manager can create TimeSlot");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Update timeslot
    @PutMapping("/update/{timeSlotID}")
    public ResponseEntity<?> updateTimeSlot(HttpServletRequest request, @PathVariable(value = "timeSlotID") long timeSlotID, @RequestBody DayOfWeek dayOfTheWeek, @RequestBody LocalTime startTime, @RequestBody LocalTime endTime) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(timeSlotService.updateTimeSlot(timeSlotID, dayOfTheWeek, startTime, endTime)));
            } else {
                return ResponseEntity.badRequest().body("Only manager can update TimeSlot");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    // Delete timeslot by id
    @DeleteMapping("/delete/{timeSlotID}")
    public ResponseEntity<?> deleteTimeSlot(HttpServletRequest request, @PathVariable(value = "timeSlotID") long timeSlotID) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(convertToDto(timeSlotService.deleteTimeSlot(timeSlotID)));
            } else {
                return ResponseEntity.badRequest().body("Only manager can delete a TimeSlot");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    private TimeSlotDto convertToDto(TimeSlot t) {
        if (t == null) {
            throw new IllegalArgumentException("TimeSlot does not exist");
        }
        TimeSlotDto timeSlotDto = new TimeSlotDto(t.getTimeSlotID(), t.getDayOfTheWeek(), t.getStartTime(),
                t.getEndTime(), t.getSystem(), t.getStaffAccount());
        return timeSlotDto;
    }

}
