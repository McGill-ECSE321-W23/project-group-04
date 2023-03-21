package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;

import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class TicketRepositoryTests {
    @Autowired
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;
    @Autowired
    private TicketRepository ticketRepository;

    // clear database after testing
    @AfterEach
    public void clearDatabase() {
        ticketRepository.deleteAll();
        parkingLotSoftwareSystemRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadTicketRepository() {

        // create an instance of the system

        ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
        parkingLotSoftwareSystem = parkingLotSoftwareSystemRepository.save(parkingLotSoftwareSystem);

        // Create a ticket
        CarType carType = CarType.Regular;
        LocalDateTime entryTime = LocalDateTime.of(2022, Month.MARCH,6,6, 6, 6 );
        Ticket ticket = new Ticket(entryTime, carType, parkingLotSoftwareSystem);
//        ticket.setParkingLotSoftwareSystem(parkingLotSoftwareSystem);

        // Save Ticket object
        ticket = ticketRepository.save(ticket);
        Long id = ticket.getTicketID();

        // Read object from database
        ticket = ticketRepository.findTicketByTicketID(id);

        // Assert that object has correct attributes
        assertNotNull(ticket);
        assertEquals(carType, ticket.getCarType());
        assertEquals(entryTime, ticket.getEntryTime());

    }

}


