
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.service.TicketService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@CrossOrigin(origins = "*")
@RestController
public class ParkingSpotRestController {
    @Autowired
    private TicketService ticketService;

    @GetMapping(value = { "/tickets", "/tickets/" })
    public List<TicketDto> getAllTickets() {
        List<TicketDto> ticketDtos = new ArrayList<>();
        for (Ticket ticket : ticketService.getAllTickets()) {
            ticketDtos.add(convertToDto(ticket));
        }
        return ticketDtos;
    }

    @PostMapping(value = { "/tickets/{entryTime}", "/tickets/{entryTime}/" })
    public TicketDto createTicket(@PathVariable("entryTime") LocalDateTime entryTime) throws IllegalArgumentException {
        Ticket ticket = ticketService.createTicket(entryTime);
        return convertToDto(ticket);
    }

    // Get a ticket by ID
    @GetMapping(value = { "/ticket/{ticketID}", "/ticket/{ticketID}/" })
    public TicketDto getTicket(@PathVariable("ticketID") long ticketID) {
        return convertToDto(ticketService.getTicket(ticketID));
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


