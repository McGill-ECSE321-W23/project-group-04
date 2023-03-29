package ca.mcgill.ecse321.parkinglotbackend.service;


import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;

@ExtendWith(MockitoExtension.class)
public class TicketServiceTest {
    @Mock
    private TicketRepository ticketDao;

    // create three tickets in the system
    @InjectMocks
    private TicketService ticketService;

    LocalDateTime time1 = LocalDateTime.now();
    LocalDateTime time2 = LocalDateTime.now();
    LocalDateTime time3 = LocalDateTime.now();

    CarType carType = CarType.valueOf("Regular");
    ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();

    Ticket ticket1 = new Ticket(time1, carType, parkingLotSoftwareSystem);
    long id1 = ticket1.getTicketID();
    Ticket ticket2 = new Ticket(time2, carType, parkingLotSoftwareSystem);
    long id2 = ticket2.getTicketID();
    Ticket ticket3 = new Ticket(time3, carType, parkingLotSoftwareSystem);
    long id3 = ticket3.getTicketID();

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(ticketDao.save(any(Ticket.class))).thenAnswer(returnParameterAsAnswer);          // Whenever anything is saved, just return the parameter object

        lenient().when(ticketDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Ticket> tickets = new ArrayList<Ticket>();
            tickets.add(ticket1);
            tickets.add(ticket2);
            tickets.add(ticket3);
            return tickets;
        });

        lenient().when(ticketDao.findTicketByTicketID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(id1)) {
                return ticket1;
            }
            else if (invocation.getArgument(0).equals(id2)){
                return ticket2;
            }
            else if (invocation.getArgument(0).equals(id3)) {
                return ticket3;
            }
            else {
                return null;
            }
        });
    }

    /**
     * Testing the creation of a ticket in the system
     */
    @Test
    public void testCreateTicket() {
        assertEquals(3, ticketService.getAllTickets().size());
        LocalDateTime time4 = LocalDateTime.now();
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
        Ticket ticket = null;
        try {
            ticket = ticketService.createTicket(time4, carType, parkingLotSoftwareSystem);
        } catch (Exception e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(ticket);
        assertEquals(time4, ticket.getEntryTime());
       // System.out.println("hello world " + ticket1.getTicketID());
    }

    /**
     * Testing Create Ticket when the system provided is null
     */
    @Test
    public void testCreateTicketNullSystem() {
        LocalDateTime entryTime = LocalDateTime.now();
        CarType carType = CarType.valueOf("Regular");
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = null;
        String error = null;
        Ticket ticket = null;
        try {
            ticket = ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
        } catch (IllegalArgumentException e) {
            error = e.getMessage();
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(ticket);
        // check error
        assertEquals("Please provide a valid system", error);
    }

    /**
     * Testing delete ticket
     * @throws Exception
     */
    @Test
    public void testDeleteTicket() throws Exception {
        String error = null;
        long ticketID = ticket1.getTicketID();
        Ticket ticket = null;
        try {
            ticket = ticketService.deleteTicket(ticketID);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(ticket);
        // check error
        assertEquals(ticket, ticket1);
    }

    /**
     * testing delete ticket when ticket id provided is wrong
     * @throws Exception
     */
    @Test
    public void testDeleteTicketNull() throws Exception {
        String error = null;
        Ticket ticket = null;
        // ticket = ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
        long ticketID = 123;
        try {
            ticket = ticketService.deleteTicket(ticketID);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(ticket);
        // check error
        assertEquals("There is no ticket in the system with this ID.", error);
    }

    /**
     * testing get ticket by TicketID
     * @throws Exception
     */
    @Test
    public void testGetTicketByID() throws Exception {
        String error = null;
        Ticket ticket4 = null;
        long ticketID = ticket1.getTicketID();
        try {
            ticket4 = ticketService.getTicketByID(ticketID);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNotNull(ticket4);
        // check error
        assertEquals(ticket1, ticket4);
    }

    /**
     * Testing get ticket when ID provided is null
     * @throws Exception
     */
    @Test
    public void testGetTicketByIDNull() throws Exception {
        String error = null;
        Ticket ticket4 = null;
        // ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
        long ticketID = 123;
        try {
            ticket4 = ticketService.getTicketByID(ticketID);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(ticket4);
        // check error
        assertEquals("There is no ticket in the system with this ID.", error);
    }

    /**
     * Testing get all tickets in the system
     * @throws Exception
     */
    @Test
    public void testGetAllTickets() throws Exception {
        String error = null;
        List<Ticket> tickets2 = null;
        try {
            tickets2 = ticketService.getAllTickets();
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals(tickets2.size(), 3);

        // check error

    }


}