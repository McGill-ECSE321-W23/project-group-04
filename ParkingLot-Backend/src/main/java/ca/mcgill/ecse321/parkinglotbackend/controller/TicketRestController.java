
package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.controller.utilities.AuthenticationUtility;
import ca.mcgill.ecse321.parkinglotbackend.dto.ParkingLotSoftwareSystemDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.TicketDto.CarTypeDto;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;
import ca.mcgill.ecse321.parkinglotbackend.service.TicketService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.util.ArrayBuilders.LongBuilder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
     * @param carTypeDto - either Regular/Large
     * @param plsDto  - the system TO
     * @return success/error message
     * @author faizchowdhury
     */

    @PostMapping("/create" )
    public ResponseEntity<?> createTicket (HttpServletRequest request,
                                           @RequestParam String type) {
        
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
            ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1);
            CarType carType = null;

            switch(type) {
                case "Large" -> carType = CarType.Large;
                case "Regular" -> carType = CarType.Regular;
            }
            Ticket t = ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
            return ResponseEntity.ok().body(convertToDto(t));
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
    public ResponseEntity<?> deleteTicket (HttpServletRequest request, @PathVariable(value = "id") long id) {

        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
                    return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }

        try {
            Ticket t = ticketService.deleteTicket(id);
            TicketDto ticketDto = convertToDto(t);

            return ResponseEntity.ok().body(ticketDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get a ticket from the system
     * @param request - staff can call this method
     * @param id - ticket ID
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getTicketByID(HttpServletRequest request, @PathVariable(value = "id") long id) throws Exception {
        // Check authorization (staff)
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            Ticket t = ticketService.getTicketByID(id);
            TicketDto ticketDto = convertToDto(t);

            return ResponseEntity.ok().body(ticketDto);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * Get all the tickets in the system
     * @param request - staff can call this method
     * @param plsDto -  the system TO
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/getAll")

        // Check authorization (staff)
        public ResponseEntity<?> getAllTickets(HttpServletRequest request) {
        try {
            if (!AuthenticationUtility.isStaff(request)) {
                return ResponseEntity.status(AuthenticationUtility.FORBIDDEN).build();
            }
        } catch (Exception e) {
            return ResponseEntity.status(AuthenticationUtility.UNAUTHORIZED).body(e.getMessage());
        }
        try {
            List<TicketDto> ticketDtos = new ArrayList<>();
            ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1);
            for (Ticket t : ticketService.getAllTickets(parkingLotSoftwareSystem)) {
                TicketDto ticketDto = convertToDto(t);
                ticketDtos.add(ticketDto);
            }

            return ResponseEntity.ok().body(ticketDtos);
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    /**
     * Get count of the Regular tickets in the system
     * @param request - staff can call this method
     * @param plsDto -  the system TO
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/getCountRegular")
    public ResponseEntity<?> getCountTicketsRegular(HttpServletRequest request) {
        try {
            List<TicketDto> ticketDtos = new ArrayList<>();
            ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1);
            for (Ticket t : ticketService.getAllTicketsRegular(parkingLotSoftwareSystem)) {
                TicketDto ticketDto = convertToDto(t);
                ticketDtos.add(ticketDto);
            }

            return ResponseEntity.ok().body(ticketDtos.size());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    /**
     * Get Cunt of large tickets in the system
     * @param request - staff can call this method
     * @param plsDto -  the system TO
     * @return success/error message
     * @author faizchowdhury
     */
    @GetMapping("/getCountLarge")
    public ResponseEntity<?> getAllTicketsLarge(HttpServletRequest request) {
        try {
            List<TicketDto> ticketDtos = new ArrayList<>();
            ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1);
            for (Ticket t : ticketService.getAllTicketsLarge(parkingLotSoftwareSystem)) {
                TicketDto ticketDto = convertToDto(t);
                ticketDtos.add(ticketDto);
            }

            return ResponseEntity.ok().body(ticketDtos.size());
        }
        catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    /**
     * Get a count of all the tickets in the system
     * @param request - anyone can call this method
     * @param plsDto - the system TO
     * @return success/error message
     * @author faizchowdhury
     */

    @GetMapping("/getCount")
    public ResponseEntity<?> getTicketCount(HttpServletRequest request) {
        try {
            ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(1);
            List <Ticket> tickets = ticketService.getAllTickets(parkingLotSoftwareSystem);
            int count = tickets.size();
            return ResponseEntity.ok().body(count);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    /**
     * converting Ticket to TO
     * @param t Ticket object
     * @return Ticket Dto
     * @author faizachowdhury
     */
    private TicketDto convertToDto(Ticket t) {
        if (t == null) {
            throw new IllegalArgumentException("There is no such ticket!");
        }
        TicketDto ticketDto = new TicketDto();
        ticketDto.setEntryTime(t.getEntryTime());
        ticketDto.setTicketID(t.getTicketID());
        ticketDto.setSystem(convertToDto(t.getSystem()));

        switch (t.getCarType()) {
            case Regular ->  ticketDto.setCarType(CarTypeDto.Regular);
            case Large -> ticketDto.setCarType(CarTypeDto.Large);
        }

        return ticketDto;
    }
    /**
     * converting pls system to plsDto
     * @param pls system object
     * @return system TO
     * @author faizachowdhury
     */
    private ParkingLotSoftwareSystemDto convertToDto(ParkingLotSoftwareSystem pls) {
        if (pls == null) {
            throw new IllegalArgumentException("There is no such system!");
        }
        ParkingLotSoftwareSystemDto plsDto = new ParkingLotSoftwareSystemDto();

        plsDto.setFeePer15m(pls.getFeePer15m());
        plsDto.setMaxStay(pls.getMaxStay());
        plsDto.setMonthlyFee(pls.getMonthlyFee());
        plsDto.setNumberOfGarages(pls.getNumberOfGarages());
        plsDto.setNumberOfLargeParkingSpots(pls.getNumberOfLargeParkingSpots());
        plsDto.setNumberOfMonthlyFloors(pls.getNumberOfMonthlyFloors());
        plsDto.setNumberOfMonthlySpotsPerFloor(pls.getNumberOfMonthlySpotsPerFloor());
        plsDto.setNumberOfRegularParkingSpots(pls.getNumberOfRegularParkingSpots());
        plsDto.setParkingLotSoftwareSystemID(pls.getParkingLotSoftwareSystemID());
        return plsDto;
    }
    /**
     * converting Ticket to ticket TO
     * @param tDto ticket TO
     * @return ticket object
     * @author faizachowdhury
     */
    private Ticket convertToDomainObject(TicketDto tDto) throws Exception {
        long id = tDto.getTicketID();
        Ticket ticket = ticketService.getTicketByID(id);
       return ticket;
    }
    /**
     * converting PLS to plsDto
     * @param plsDto system TO
     * @return system object
     * @author faizachowdhury
     */
    private ParkingLotSoftwareSystem convertToDomainObject(ParkingLotSoftwareSystemDto plsDto) throws Exception {
        long id = plsDto.getParkingLotSoftwareSystemID();
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(id);
        return parkingLotSoftwareSystem;
    }


}

