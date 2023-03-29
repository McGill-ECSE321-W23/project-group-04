
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;

import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import ca.mcgill.ecse321.parkinglotbackend.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/ticket")
public class TicketRestController {
    @Autowired
    private TicketService ticketService;


    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    /**
     * Create a ticket in the system
     * @param request - staff can call this method
     * @param carType - either Regular/Large
     * @param parkingLotSoftwareSystem  - the system
     * @return success/error message
     * @author faizchowdhury
     */

    @PostMapping("/create" )
    public ResponseEntity<?> createTicket (HttpServletRequest request,  @RequestBody CarType carType,
                                           @RequestBody ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
        
         // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        try {
            LocalDateTime entryTime = LocalDateTime.now();
            ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Delete a ticket from the system
     * @param request - staff can call this method
     * @param id - ticket id
     * @return success/error message
     * @author faizchowdhury
     */

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket (HttpServletRequest request, @RequestBody long id) {

        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get a ticket from the system
     * @param request - staff can call this method
     * @param ticketID - ticket ID
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTicketByID(HttpServletRequest request, @RequestBody long ticketID) throws Exception {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        try {
            Ticket t = ticketService.getTicketByID(ticketID);
            TicketDto ticketDto = new TicketDto();

            ticketDto.setSystem(t.getSystem());
            ticketDto.setCarType(t.getCarType());
            ticketDto.setTicketID(t.getTicketID());
            ticketDto.setEntryTime(t.getEntryTime());

            return ResponseEntity.ok().body(ticketDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * Get all the tickets in the system
     * @param request - staff can call this method
     * @param parkingLotSoftwareSystem -  the system
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/tickets")
    public ResponseEntity<?> getAllTickets(HttpServletRequest request, @RequestBody ParkingLotSoftwareSystem
            parkingLotSoftwareSystem) {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            List<TicketDto> ticketDtos = new ArrayList<>();
            for (Ticket t : ticketService.getAllTickets(parkingLotSoftwareSystem)) {
                TicketDto ticketDto = new TicketDto();

                ticketDto.setSystem(t.getSystem());
                ticketDto.setCarType(t.getCarType());
                ticketDto.setTicketID(t.getTicketID());
                ticketDto.setEntryTime(t.getEntryTime());
                ticketDtos.add(ticketDto);
            }

            return ResponseEntity.ok().body(ticketDtos);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get a count of all the tickets in the system
     * @param request - anyone can call this method
     * @param parkingLotSoftwareSystem - the system
     * @return success/error message
     * @author faizchowdhury
     */

    @GetMapping("/getCount")
    public ResponseEntity<?> getTicketCount(HttpServletRequest request, @RequestBody ParkingLotSoftwareSystem
            parkingLotSoftwareSystem) {
        try {
            List <Ticket> tickets = ticketService.getAllTickets(parkingLotSoftwareSystem);
            int count = tickets.size();
            return ResponseEntity.ok().body(count);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


}

