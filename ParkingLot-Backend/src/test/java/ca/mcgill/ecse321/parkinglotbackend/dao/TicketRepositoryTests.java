package ca.mcgill.ecse321.parkinglotbackend.dao;

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
    private TicketRepository ticketRepository;

    @AfterEach
    public void clearDatabase() {
        ticketRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadTicketRepository() {
        // Create a ticket
        String id = "test";
        CarType carType = CarType.Regular;
        LocalDateTime entryTime = LocalDateTime.of(2022, Month.MARCH,6,6, 6, 6 );

        Ticket ticket = new Ticket(id, entryTime,carType);

        // Save object
        ticket = ticketRepository.save(ticket);

        // Read object from database
        ticket = ticketRepository.findTicketByTicketID(id);

        // Assert that object has correct attributes
        assertNotNull(ticket);
        assertEquals(carType, ticket.getCarType());
        assertEquals(entryTime, ticket.getEntryTime());

    }

}


