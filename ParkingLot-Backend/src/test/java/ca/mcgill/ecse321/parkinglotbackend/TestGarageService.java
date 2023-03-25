package ca.mcgill.ecse321.parkinglotbackend;

import ca.mcgill.ecse321.parkinglotbackend.service.GarageService;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class TestGarageService {
    @Mock
    private GarageRepository garageDao;

    @InjectMocks
    private GarageService garageService;

    // Data inside database
    private static final int garageNumberExisting1 = 1;
    private static final int garageNumberExisting2 = 2;
    private static final int garageNumberExisting3 = 3;
    private static final int garageNumberNewCreation = 4;
    private static final int garageNumberModification = 5;
    private static final int garageNumberNonExistent = 6;
    private static final int garageNumberNegative = -1;
    private static final int garageNumberZero = 0;
    private static final int garageNumberExceed = Integer.MAX_VALUE + 1;


    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(garageDao.save(any(Garage.class))).thenAnswer(returnParameterAsAnswer);          // Whenever anything is saved, just return the parameter object

        lenient().when(garageDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<Garage> garages = new ArrayList<Garage>();
            garages.add(new Garage(garageNumberExisting1, garageNumberExisting1));
            garages.add(new Garage(garageNumberExisting2, garageNumberExisting2));
            garages.add(new Garage(garageNumberExisting3, garageNumberExisting3));
            return garages;
        });

        lenient().when(garageDao.findGarageByGarageID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals((long) 1)) {
                return new Garage(garageNumberExisting1, garageNumberExisting1);
            }
            else if (invocation.getArgument(0).equals((long) 2)){
                return new Garage(garageNumberExisting2, garageNumberExisting2);
            }
            else if (invocation.getArgument(0).equals((long) 3)) {
                return new Garage(garageNumberExisting3, garageNumberExisting3);
            }
            else {
                return null;
            }
        });
    }

    // Tests

    @Test
    public void testCreateGarage() {
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumberNewCreation);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garage);
        assertEquals(garageNumberNewCreation, garage.getGarageNumber());
    }

    @Test
    public void testCreateGarageNonPositiveGarageNumber() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumberNegative);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number must be positive.", error);
    }

    @Test
    public void testCreateGarageExistentGarageNumber() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumberExisting2);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("This garage number already exists.", error);
    }

    @Test
    public void testCreateGarageExceedingIntLimitGarageNumber() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumberExceed);   // Should get a negative number because of overflow
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number must be positive.", error);
    }

    @Test
    public void testDeleteGarage() {
        Garage garage = null;
        try {
            garage = garageService.deleteGarageService(garageNumberExisting3);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garage);
    }

    @Test
    public void testDeleteNonExistentGarage() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.deleteGarageService(garageNumberNonExistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage not found.", error);
    }

    @Test
    public void testModifyGarage() {
        String error = null;
        Garage garageModified = null;
        try {
            garageModified = garageService.modifyGarage(garageNumberExisting1, garageNumberModification);
        } catch (Exception e) {
            error = e.getMessage();
            assertEquals("", error);
            fail();
        }
        assertNotNull(garageModified);
        assertEquals(garageNumberExisting1, garageModified.getGarageID());
        assertNotEquals(garageNumberExisting1, garageModified.getGarageNumber());
    }

    @Test
    public void testModifyNonExistentGarage() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.modifyGarage(garageNumberNonExistent, garageNumberModification);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage not found.", error);
    }

    @Test
    public void testModifyGarageNonPositiveGarageNumber() {
        String error = null;
        Garage garageModified = null;
        try {
            garageModified = garageService.modifyGarage(garageNumberExisting1, garageNumberZero);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("Garage number must be positive.", error);
    }

    @Test
    public void testModifyGarageExistentGarageNumber() {
        String error = null;
        Garage garageModified = null;
        try {
            garageModified = garageService.modifyGarage(garageNumberExisting1, garageNumberExisting2);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("This garage number already exists.", error);
    }

    @Test
    public void testGetGarage() {
        Garage garageRetrieved = null;
        try {
            garageRetrieved = garageService.getGarageService(garageNumberExisting2);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garageRetrieved);
        assertEquals(garageNumberExisting2, garageRetrieved.getGarageNumber());
    }

    @Test
    public void testGetGarageNonExistent() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.getGarageService(garageNumberNonExistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage not found.", error);
    }

    @Test
    public void testGetAllGarages() {
        List<Garage> garages = garageService.getAllGarageService();
        assertEquals(3, garages.size());
    }
}
