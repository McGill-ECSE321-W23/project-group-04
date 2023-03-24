
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;

import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
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
public class TicketRestController {
    @Autowired
    private TicketService ticketService;


    @GetMapping(value = { "/tickets", "/tickets/" })
    public int numberOfTickets() {
        int number = ticketService.numberOfTickets();
        return number;
    }



    @GetMapping(value = { "/tickets", "/tickets/" })
    public ResponseEntity<?> getAllTickets(HttpServletRequest request) {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }


        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : ticketService.getAllTickets()) {
            ticketDtos.add(convertToDto(ticket));
        }
        return ticketDtos;
    }



    @PostMapping(value = {"/createTicket", "/createTicket/" })
    public ResponseEntity<?> createTicket (HttpServletRequest request, @RequestBody LocalDateTime
            entryTime, @RequestBody CarType carType, @PathVariable ParkingLotSoftwareSystem parkingLotSoftwareSystem) {
        try {
            ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

//    @PostMapping(value = { "/tickets/{entryTime}", "/tickets/{entryTime}/" })
//    public TicketDto createTicket(@PathVariable("entryTime") LocalDateTime entryTime) throws IllegalArgumentException {
//        Ticket ticket = ticketService.createTicket(entryTime);
//        return convertToDto(ticket);
//    }

    // Get a ticket by ID
    @GetMapping(value = { "/ticket/{ticketID}", "/ticket/{ticketID}/" })
    public TicketDto getTicket(@PathVariable("ticketID") long ticketID) throws Exception {
        return convertToDto(ticketService.getTicketByID(ticketID));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteTicket(HttpServletRequest request, @PathVariable(value = "id") long id) {
        try {
            ticketService.deleteTicket(id);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    private TicketDto convertToDto(Ticket t) {
        if (t == null) {
            throw new IllegalArgumentException("There is no such Ticket!");
        }

        TicketDto ticketDto = new TicketDto(t.getEntryTime());

        ticketDto.setSystem(t.getSystem());
        ticketDto.setCarType(t.getCarType());
        ticketDto.setTicketID(t.getTicketID());


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


