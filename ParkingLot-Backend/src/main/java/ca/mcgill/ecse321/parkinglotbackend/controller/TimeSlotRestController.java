package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.TimeSlotDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.StaffAccount;
import ca.mcgill.ecse321.parkinglotbackend.model.TimeSlot;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import ca.mcgill.ecse321.parkinglotbackend.service.StaffAccountService;
import ca.mcgill.ecse321.parkinglotbackend.service.TimeSlotService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.stream.Collectors;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/timeslot")
public class TimeSlotRestController {
    @Autowired
    private TimeSlotService timeSlotService;
    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;
    @Autowired
    private StaffAccountService staffAccountService;

    /**
     * Get timeSlot by its id
     * @param request
     * @param timeSlotID
     * @return
     */
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

    /**
     * Get all timeSlots
     * @param request
     * @return
     */
    @GetMapping("/getAll")
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

    /**
     * Get all timeSlots of a staff account by accountID
     * @param request
     * @param accountID
     * @return
     */
    @GetMapping("/getAll/{accountID}")
    public ResponseEntity<?> getAllTimeSlotsByAccountID(HttpServletRequest request, @PathVariable(value = "accountID") long accountID) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(timeSlotService.getTimeSlotsByStaffAccountID(accountID).stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            } else {
                return ResponseEntity.badRequest().body("Only manager can get all TimeSlots by accountID");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get all open hours
     * @param request
     * @return
     */
    @GetMapping("/getOpen")
    public ResponseEntity<?> getAllOpenHours(HttpServletRequest request) {
        // Check authorization
        try {
            if (AuthenticationUtility.isManager(request)) {
                return ResponseEntity.ok(timeSlotService.getAllOpenHours().stream().map(g -> convertToDto(g)).collect(Collectors.toList()));
            } else {
                return ResponseEntity.badRequest().body("Only manager can get opening hours");
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Create a timeslot
     * @param request
     * @param dayOfTheWeek
     * @param startTime
     * @param endTime
     * @param parkingLotSoftwareSystemID
     * @param accountID
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<?> createTimeSlot(HttpServletRequest request, @RequestParam DayOfWeek dayOfTheWeek, @RequestParam LocalTime startTime, @RequestParam LocalTime endTime, @RequestParam long parkingLotSoftwareSystemID, @RequestParam long accountID) {
        try {
            ParkingLotSoftwareSystem system = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingLotSoftwareSystemID);
            StaffAccount staffAccount = staffAccountService.getStaffAccount(accountID);
            if ((staffAccount == null && system == null) || (staffAccount != null && system != null)) {
                return ResponseEntity.badRequest().body("Cannot create timeslot");
            }
            // Check authorization
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

    /**
     * Update a timeslot
     * @param request
     * @param timeSlotID
     * @param dayOfTheWeek
     * @param startTime
     * @param endTime
     * @return
     */
    @PutMapping("/update/{timeSlotID}")
    public ResponseEntity<?> updateTimeSlot(HttpServletRequest request, @PathVariable(value = "timeSlotID") long timeSlotID, @RequestParam DayOfWeek dayOfTheWeek, @RequestParam LocalTime startTime, @RequestParam LocalTime endTime) {
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

    /**
     * Delete a timeslot
     * @param request
     * @param timeSlotID
     * @return
     */
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
        TimeSlotDto timeSlotDto = new TimeSlotDto(
                t.getTimeSlotID(),
                t.getDayOfTheWeek(),
                t.getStartTime(),
                t.getEndTime(),
                t.getSystem().getParkingLotSoftwareSystemID(),
                t.getStaffAccount().getAccountID());
        return timeSlotDto;
    }

}
