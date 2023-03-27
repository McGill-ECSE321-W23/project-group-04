package ca.mcgill.ecse321.parkinglotbackend;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.ParkingLotSoftwareSystemRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.ParkingLotSoftwareSystem;
import ca.mcgill.ecse321.parkinglotbackend.service.ParkingLotSoftwareSystemService;

/**
 * @author Qin Xuan Xu
 * using template from tutorials
 */
@ExtendWith(MockitoExtension.class)
public class TestParkingLotSoftwareSystemService {
    @Mock
    private ParkingLotSoftwareSystemRepository parkingLotSoftwareSystemRepository;
    @InjectMocks
    private ParkingLotSoftwareSystemService parkingLotSoftwareSystemService;

    // Data for testing
    private static final long parkingSystemId = 1L;

    private static final float monthlyFee = 3.3f;
    private static final float feePer15m = 3.3f;
    private static final int maxStay = 3;
    private static final int numberOfRegularParkingSpots = 30;
    private static final int numberOfLargeParkingSpots = 30;
    private static final int numberOfMonthlyFloors = 3;
    private static final int numberOfMonthlySpotsPerFloor = 30;
    private static final int numberOfGarages = 3;

    private static final float monthlyFeeNegative = -3.3f;
    private static final float feePer15mNegative = -3.3f;
    private static final int maxStayNegative = -3;
    private static final int numberOfRegularParkingSpotsNegative = -30;
    private static final int numberOfLargeParkingSpotsNegative = -30;
    private static final int numberOfMonthlyFloorsNegative = -3;
    private static final int numberOfMonthlySpotsPerFloorNegative = -30;
    private static final int numberOfGaragesNegative = -3;

    private static final float monthlyFeeUpdate = 4.4f;
    private static final float feePer15mUpdate = 4.4f;
    private static final int maxStayUpdate = 4;
    private static final int numberOfRegularParkingSpotsUpdate = 40;
    private static final int numberOfLargeParkingSpotsUpdate = 40;
    private static final int numberOfMonthlyFloorsUpdate = 4;
    private static final int numberOfMonthlySpotsPerFloorUpdate = 40;
    private static final int numberOfGaragesUpdate = 4;

    private static final long parkingSystemIdNonexistent = 69L;

    // Setup mock repository
    @BeforeEach
    public void setMockOutput() {
        lenient().when(parkingLotSoftwareSystemRepository.findParkingLotSoftwareSystemByParkingLotSoftwareSystemID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(parkingSystemId)) {
                ParkingLotSoftwareSystem parkingLotSoftwareSystem = new ParkingLotSoftwareSystem();
                parkingLotSoftwareSystem.setParkingLotSoftwareSystemID(parkingSystemId);
                return parkingLotSoftwareSystem;
            } else {
                return null;
            }
        });
        // Whenever anything is saved, just return the parameter object
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(parkingLotSoftwareSystemRepository.save(any(ParkingLotSoftwareSystem.class))).thenAnswer(returnParameterAsAnswer);
    }

    // Test creation of a parking lot software system
    @Test
    public void testCreateParkingLotSoftwareSystem() {
        ParkingLotSoftwareSystem system = null;
        try {
            system = parkingLotSoftwareSystemService.createParkingLotSoftwareSystem(monthlyFee, feePer15m, maxStay, numberOfRegularParkingSpots, numberOfLargeParkingSpots, numberOfMonthlyFloors, numberOfMonthlySpotsPerFloor, numberOfGarages);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(system);
        assertEquals(system.getMonthlyFee(), monthlyFee);
        assertEquals(system.getFeePer15m(), feePer15m);
        assertEquals(system.getMaxStay(), maxStay);
        assertEquals(system.getNumberOfRegularParkingSpots(), numberOfRegularParkingSpots);
        assertEquals(system.getNumberOfLargeParkingSpots(), numberOfLargeParkingSpots);
        assertEquals(system.getNumberOfMonthlyFloors(), numberOfMonthlyFloors);
        assertEquals(system.getNumberOfMonthlySpotsPerFloor(), numberOfMonthlySpotsPerFloor);
        assertEquals(system.getNumberOfGarages(), numberOfGarages);
    }

    // Test creation of a parking lot software system with negative values
    @Test
    public void testCreateParkingLotSoftwareSystemNegative() {
        ParkingLotSoftwareSystem system = null;
        String error = null;
        try {
            system = parkingLotSoftwareSystemService.createParkingLotSoftwareSystem(monthlyFeeNegative, feePer15mNegative, maxStayNegative, numberOfRegularParkingSpotsNegative, numberOfLargeParkingSpotsNegative, numberOfMonthlyFloorsNegative, numberOfMonthlySpotsPerFloorNegative, numberOfGaragesNegative);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(system);
        assertEquals("Values cannot be negative!", error);
    }

    // Test getting a parking lot software system
    @Test
    public void testGetParkingLotSoftwareSystem() {
        ParkingLotSoftwareSystem system = null;
        try {
            system = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingSystemId);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(system);
        assertEquals(system.getParkingLotSoftwareSystemID(), parkingSystemId);
    }

    // Test getting a parking lot software system that doesn't exist
    @Test
    public void testGetParkingLotSoftwareSystemNonexistent() {
        ParkingLotSoftwareSystem system = null;
        String error = null;
        try {
            system = parkingLotSoftwareSystemService.getParkingLotSoftwareSystem(parkingSystemIdNonexistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(system);
        assertEquals("No system with this id exists!", error);
    }

    // Test updating a parking lot software system
    @Test
    public void testUpdateParkingLotSoftwareSystem() {
        ParkingLotSoftwareSystem system = null;
        try {
            system = parkingLotSoftwareSystemService.updateParkingLotSoftwareSystem(parkingSystemId, monthlyFeeUpdate, feePer15mUpdate, maxStayUpdate, numberOfRegularParkingSpotsUpdate, numberOfLargeParkingSpotsUpdate, numberOfMonthlyFloorsUpdate, numberOfMonthlySpotsPerFloorUpdate, numberOfGaragesUpdate);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(system);
        assertEquals(system.getMonthlyFee(), monthlyFeeUpdate);
        assertEquals(system.getFeePer15m(), feePer15mUpdate);
        assertEquals(system.getMaxStay(), maxStayUpdate);
        assertEquals(system.getNumberOfRegularParkingSpots(), numberOfRegularParkingSpotsUpdate);
        assertEquals(system.getNumberOfLargeParkingSpots(), numberOfLargeParkingSpotsUpdate);
        assertEquals(system.getNumberOfMonthlyFloors(), numberOfMonthlyFloorsUpdate);
        assertEquals(system.getNumberOfMonthlySpotsPerFloor(), numberOfMonthlySpotsPerFloorUpdate);
        assertEquals(system.getNumberOfGarages(), numberOfGaragesUpdate);
    }

    // Test updating a parking lot software system that doesn't exist
    @Test
    public void testUpdateParkingLotSoftwareSystemNonexistent() {
        ParkingLotSoftwareSystem system = null;
        String error = null;
        try {
            system = parkingLotSoftwareSystemService.updateParkingLotSoftwareSystem(parkingSystemIdNonexistent, monthlyFeeUpdate, feePer15mUpdate, maxStayUpdate, numberOfRegularParkingSpotsUpdate, numberOfLargeParkingSpotsUpdate, numberOfMonthlyFloorsUpdate, numberOfMonthlySpotsPerFloorUpdate, numberOfGaragesUpdate);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(system);
        assertEquals("No system with this id exists!", error);
    }

    // Test updating a parking lot software system with negative values
    @Test
    public void testUpdateParkingLotSoftwareSystemNegative() {
        ParkingLotSoftwareSystem system = null;
        String error = null;
        try {
            system = parkingLotSoftwareSystemService.updateParkingLotSoftwareSystem(parkingSystemId, monthlyFeeNegative, feePer15mNegative, maxStayNegative, numberOfRegularParkingSpotsNegative, numberOfLargeParkingSpotsNegative, numberOfMonthlyFloorsNegative, numberOfMonthlySpotsPerFloorNegative, numberOfGaragesNegative);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(system);
        assertEquals("Values cannot be negative!", error);
    }

    // Test deleting a parking lot software system
    @Test
    public void testDeleteParkingLotSoftwareSystem() {
        ParkingLotSoftwareSystem system = null;
        try {
            system = parkingLotSoftwareSystemService.deleteParkingLotSoftwareSystem(parkingSystemId);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(system);
    }

    // Test deleting a parking lot software system that doesn't exist
    @Test
    public void testDeleteParkingLotSoftwareSystemNonexistent() {
        ParkingLotSoftwareSystem system = null;
        String error = null;
        try {
            system = parkingLotSoftwareSystemService.deleteParkingLotSoftwareSystem(parkingSystemIdNonexistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(system);
        assertEquals("No system with this id exists!", error);
    }
    
}
