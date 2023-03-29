
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;

import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingSpotService;
import ca.mcgill.ecse321.parkinglotbackend.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.cglib.core.Local;
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

    //TODO: add the system to create, delete, update methods?
    @Autowired
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;


    //create a ticket in the system
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



    // delete a ticket by id

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

    // Get a ticket by ID
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


    // get a list of all the tickets
    @GetMapping("/tickets")
    public ResponseEntity<?> getAllTickets(HttpServletRequest request) {
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
            for (Ticket t : ticketService.getAllTickets()) {
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

    // get no. of tickets in the system

    @GetMapping("/getCount")
    public ResponseEntity<?> getTicketCount(HttpServletRequest request) {
        try {
            List <Ticket> tickets = ticketService.getAllTickets();
            int count = tickets.size();
            return ResponseEntity.ok().body(count);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private TicketDto convertToDto(Ticket t) {
        if (t == null) {
            throw new IllegalArgumentException("There is no such Ticket!");
        }

        TicketDto ticketDto = new TicketDto();

        ticketDto.setSystem(t.getSystem());
        ticketDto.setCarType(t.getCarType());
        ticketDto.setTicketID(t.getTicketID());
        ticketDto.setEntryTime(t.getEntryTime());


        return ticketDto;
    }
    private Ticket convertToDomainObject(TicketDto tDto) {
        List<Ticket> tickets = ticketService.getAllTickets();
        for (Ticket ticket : tickets) {
            if (ticket.getTicketID() ==  (tDto.getTicketID())) {
                return ticket;
            }
        }
        return null;
    }




}

