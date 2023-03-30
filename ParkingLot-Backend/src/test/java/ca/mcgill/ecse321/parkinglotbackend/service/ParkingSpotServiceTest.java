package ca.mcgill.ecse321.parkinglotbackend.service;


import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.MonthlyReservation;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingSpot;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.lenient;


import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;


/**
 * A class to test the Parking Spot Service class
 * @author faizachowdhury
 */
@ExtendWith(MockitoExtension.class)
public class ParkingSpotServiceTest {
    @Mock
    private ParkingSpotRepository parkingSpotDao;

    @InjectMocks
    private ParkingSpotService parkingSpotService;
    //private static final long TICKET_KEY = 111;

    long id1 = 123;
    long id2 = 456;

    int floor1 = 1;
    int floor2 = 2;

    int num1 = 10;
    int num2 = 20;

    ParkingSpot parkingSpot1 = new ParkingSpot(id1, floor1, num1);
    ParkingSpot parkingSpot2 = new ParkingSpot(id2, floor2, num2);


    /**
     * Create mock DAO
     * @author faizachowdhury
     */
    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };
        lenient().when(parkingSpotDao.save(any(ParkingSpot.class))).thenAnswer(returnParameterAsAnswer);          // Whenever anything is saved, just return the parameter object

        lenient().when(parkingSpotDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<ParkingSpot> parkingSpots = new ArrayList<ParkingSpot>();
            parkingSpots.add(parkingSpot1);
            parkingSpots.add(parkingSpot2);
            return parkingSpots;
        });

        lenient().when(parkingSpotDao.findParkingSpotByParkingSpotID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(id1)) {
                return parkingSpot1;
            }
            else if (invocation.getArgument(0).equals(id2)){
                return parkingSpot2;
            }

            else {
                return null;
            }
        });

    }

    /**
     * Test the creation of a Parking Spot
     * @author faizachowdhury
     */
    @Test
    public void testCreateParkingSpot() {
        assertEquals(2, parkingSpotService.findAllParkingSpots().size());
        ParkingSpot parkingSpot = null;
        try {
            parkingSpot = parkingSpotService.createParkingSpot(3, 30);
        } catch (Exception e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(parkingSpot);
        assertEquals(3, parkingSpot.getFloor());
    }
    /**
     * Testing updating a Parking Spot in the system
     * @author faizachowdhury
     */
    @Test
    public void testUpdateParkingSpot() {
        ParkingSpot parkingSpot = null;
        try {
            parkingSpot = parkingSpotService.updateParkingSpot(id1, 5, 50);
        } catch (Exception e) {
            // Check that no error occurred
            fail();
        }
        assertNotNull(parkingSpot);
        assertEquals(parkingSpot1.getFloor(), 5);
    }
    /**
     * Testing updating a Parking Spot when it doesn't exist in the system
     * @author faizachowdhury
     */
    @Test
    public void testUpdateParkingSpotNullID() throws Exception {
        String error = null;
        ParkingSpot parkingSpot = null;
        try {
            parkingSpot = parkingSpotService.updateParkingSpot(789, 5, 50);
        } catch (Exception e) {
            error = e.getMessage();

        }
        assertNull(parkingSpot);
        assertEquals(error,"There is no Parking Spot in the system with this ID.");
    }

    /**
     * Testing deleting a Parking Spot in the system
     * @author faizachowdhury
     */
    @Test
    public void testDeleteParkingSpot() throws Exception {
        String error = null;
        long parkingSpotID = parkingSpot1.getParkingSpotID();
        ParkingSpot parkingSpot = null;
        try {
            parkingSpot = parkingSpotService.deleteParkingSpot(parkingSpotID);
        }
        catch (IllegalArgumentException e) {
            fail();
        }
        assertNotNull(parkingSpot);
        // check error
        assertEquals(parkingSpot, parkingSpot1);
    }
    /**
     * Testing delete a Parking Spot when it is null
     * @author faizachowdhury
     */
    @Test
    public void testDeleteParkingSpotNull() throws Exception {
        String error = null;
        long parkingSpotID = 789;
        ParkingSpot parkingSpot = null;
        try {
            parkingSpot = parkingSpotService.deleteParkingSpot(parkingSpotID);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(parkingSpot);
        // check error
        assertEquals("There is no Parking Spot in the system with this ID.", error);
    }
    /**
     * Testing getting a Parking Spot from the system
     * @author faizachowdhury
     */
    @Test
    public void testGetParkingSpotByID() throws Exception {
        String error = null;
        ParkingSpot parkingSpot4 = null;
        try {
            parkingSpot4 = parkingSpotService.findParkingSpotByID(id1);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNotNull(parkingSpot4);
        // check error
        assertEquals(parkingSpot1, parkingSpot4);
    }
    /**
     * Testing getting a Parking Spot from the system when it is null
     * @author faizachowdhury
     */
    @Test
    public void testGetTicketByIDNull() throws Exception {
        String error = null;
        ParkingSpot parkingSpot4 = null;
        try {
            parkingSpot4 = parkingSpotService.findParkingSpotByID(789);
        }
        catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(parkingSpot4);
        // check error
        assertEquals("There is no Parking Spot in the system with this ID.", error);
    }
    /**
     * Testing getting the monthly reservation of a parking spot
     * @author faizachowdhury
     */
    @Test
    public void testGetMonthlyReservation() throws Exception {
        String error = null;
        MonthlyReservation monthlyReservation = new MonthlyReservation();
        MonthlyReservation monthlyReservation2 = null;
        parkingSpot1.setMonthlyReservation(monthlyReservation);
        try {
            monthlyReservation2 = parkingSpotService.findMonthlyReservation(123);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNotNull(monthlyReservation2);
        // check error
        assertEquals(monthlyReservation, monthlyReservation2);
    }
    /**
     * Testing getting the monthly reservation of a parking spot when it is null
     * @author faizachowdhury
     */
    @Test
    public void testGetMonthlyReservationNull() throws Exception {
        String error = null;
        MonthlyReservation monthlyReservation = null;
        try {
            monthlyReservation = parkingSpotService.findMonthlyReservation(456);
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(monthlyReservation);
        // check error
        assertEquals(error, "There is no monthly reservation for this Parking Spot.");
    }
    /**
     * Testing getting a list of all the parking spots in the system
     * @author faizachowdhury
     */
    @Test
    public void testGetAllParkingSpots() throws Exception {
        String error = null;
        List<ParkingSpot> parkingSpots2 = null;
        try {
            parkingSpots2 = parkingSpotService.findAllParkingSpots();
        }
        catch (Exception e) {
            error = e.getMessage();
        }
        assertEquals(parkingSpots2.size(), 2);
        assertEquals(parkingSpots2.get(0), parkingSpot1);
        assertEquals(parkingSpots2.get(1), parkingSpot2);
    }


}