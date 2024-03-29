package ca.mcgill.ecse321.parkinglotbackend.service;


import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket;
import ca.mcgill.ecse321.parkinglotbackend.model.Ticket.CarType;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.TicketRepository;

/**
 * A class to test the Ticket Service class
 * using template from tutorials
 * @author faizachowdhury
 */
@ExtendWith(MockitoExtension.class)
public class TestTicketService {
    @Mock
    private TicketRepository ticketDao;

    // create three tickets in the system
    @InjectMocks
    private TicketService ticketService;

    long id1 = 123;
    long id2 = 456;
    long id3 = 789;

    LocalDateTime time1 = LocalDateTime.now();
    LocalDateTime time2 = LocalDateTime.now();
    LocalDateTime time3 = LocalDateTime.now();

    CarType carType = CarType.Regular;
    CarType carType2 = CarType.Large;
    ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();

    Ticket ticket1 = new Ticket(id1, time1, carType, parkingLotSoftwareSystem);
    Ticket ticket2 = new Ticket(id2, time2, carType2, parkingLotSoftwareSystem);
    Ticket ticket3 = new Ticket(id3, time3, carType, parkingLotSoftwareSystem);


    /**
     * Create mock DAO
     * @author faizachowdhury
     */
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
     * Testing the successful creation of a ticket in the system
     *  @author faizachowdhury
     */
    @Test
    public void testCreateTicket() {
        assertEquals(3, ticketService.getAllTickets(parkingLotSoftwareSystem).size());
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
    }

    /**
     * Testing Create Ticket when the system provided is null
     *  @author faizachowdhury
     */
    @Test
    public void testCreateTicketNullSystem() {
        LocalDateTime entryTime = LocalDateTime.now();
        CarType carType = CarType.Regular;
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
     * Testing Create Ticket when the Car Type provided is null
     *  @author faizachowdhury
     */
    @Test
    public void testCreateTicketNullCarType() {
        LocalDateTime entryTime = LocalDateTime.now();
        CarType carType = null;
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
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
        assertEquals("Please provide a valid Car Type", error);
    }
    /**
     * Testing Create Ticket when entry time provided is null
     *  @author faizachowdhury
     */
    @Test
    public void testCreateTicketNullEntryTime() {
        LocalDateTime entryTime = null;
        CarType carType = CarType.Regular;
        ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
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
        assertEquals("Please provide a valid entry time", error);
    }


    /**
     * Testing delete ticket
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testDeleteTicket() throws Exception {
        // String error = null;
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
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testDeleteTicketNull() throws Exception {
        String error = null;
        Ticket ticket = null;
        // ticket = ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
        long ticketID = 321;
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
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetTicketByID() throws Exception {
        String error = "";
        Ticket ticket4 = null;
        long ticketID = ticket1.getTicketID();
        try {
            ticket4 = ticketService.getTicketByID(ticketID);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNotNull(ticket4);
        assertEquals("", error);
        assertEquals(ticket1, ticket4);
    }

    /**
     * Testing get ticket when ID provided is null
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetTicketByIDNull() throws Exception {
        String error = null;
        Ticket ticket4 = null;
        // ticketService.createTicket(entryTime, carType, parkingLotSoftwareSystem);
        long ticketID = 321;
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
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetAllTickets() throws Exception {
        String error = "";
        List<Ticket> tickets2 = null;
        try {
            tickets2 = ticketService.getAllTickets(parkingLotSoftwareSystem);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals("", error);
        assertEquals(tickets2.size(), 3);
        assertEquals(tickets2.get(0), ticket1);
        assertEquals(tickets2.get(1), ticket2);
        assertEquals(tickets2.get(2), ticket3);
    }
    /**
     * Testing get all Regular tickets in the system
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetAllTicketsRegular() throws Exception {
        String error = "";
        List<Ticket> tickets2 = null;
        try {
            tickets2 = ticketService.getAllTicketsRegular(parkingLotSoftwareSystem);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals(tickets2.size(), 2);
        assertEquals("", error);
    }
    /**
     * Testing get all Large tickets in the system
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetAllTicketsLarge() throws Exception {
        String error = "";
        List<Ticket> tickets2 = null;
        try {
            tickets2 = ticketService.getAllTicketsLarge(parkingLotSoftwareSystem);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals(tickets2.size(), 1);
        assertEquals("", error);
    }
    /**
     * Testing get ticket count in the system
     * @throws Exception if error occurred
     *  @author faizachowdhury
     */
    @Test
    public void testGetTicketCount() throws Exception {
        String error = "";
        int count = 0;
        try {
           count = ticketService.numberOfTickets(parkingLotSoftwareSystem);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals(count, 3);
        assertEquals("", error);
    }


}