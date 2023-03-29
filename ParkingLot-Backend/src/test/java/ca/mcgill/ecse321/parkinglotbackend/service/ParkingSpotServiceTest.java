package ca.mcgill.ecse321.parkinglotbackend.service;


import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingSpotRepository;
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



@ExtendWith(MockitoExtension.class)
public class ParkingSpotServiceTest {
    @Mock
    private ParkingSpotRepository parkingSpotDao;

    @InjectMocks
    private ParkingSpotService parkingSpotService;
    //private static final long TICKET_KEY = 111;

    int floor1 = 1;
    int floor2 = 2;

    int num1 = 10;
    int num2 = 20;

    ParkingSpot parkingSpot1 = new ParkingSpot(floor1, num1);
    long id1 = parkingSpot1.getParkingSpotID();

    ParkingSpot parkingSpot2 = new ParkingSpot(floor2, num2);
    long id2 = parkingSpot2.getParkingSpotID();

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
    @Test
    public void testDeleteParkingSpotNull() throws Exception {
        String error = null;
        long parkingSpotID = 123;
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
    @Test
    public void testGetTicketByIDNull() throws Exception {
        String error = null;
        ParkingSpot parkingSpot4 = null;
        try {
            parkingSpot4 = parkingSpotService.findParkingSpotByID(123);
        }
        catch (Exception e) {
            error = e.getMessage();
        }

        assertNull(parkingSpot4);
        // check error
        assertEquals("There is no Parking Spot in the system with this ID.", error);
    }
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

        // check error

    }


}