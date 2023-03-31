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
     * @param request - only staff can access this method
     * @param timeSlotID - unique ID of time slot object
     * @return error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @return error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @param accountID - unique account id
     * @return error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @return  error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @param dayOfTheWeek - the day of the week of the timeslot
     * @param startTime - start of timeslot
     * @param endTime - end of timeslot
     * @param parkingLotSoftwareSystemID - system ID
     * @param accountID - account ID
     * @return error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @param timeSlotID - ID of the timeslot
     * @param dayOfTheWeek - the day of the week of the timeslot
     * @param startTime - start of timeslot
     * @param endTime - end of timeslot
     * @return error message if encountered
     * @author Qin Xuan Xu
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
     * @param request - only manager can access this method
     * @param timeSlotID - timeslot ID
     * @return error message if encountered
     * @author Qin Xuan Xu
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

    /**
     * method to convert timeslot object to timeslot TO
     * @param t time slot obj
     * @return time slot TO
     * @author Qin Xuan Xu
     */
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
