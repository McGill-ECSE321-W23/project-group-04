package ca.mcgill.ecse321.parkinglotbackend.service;

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

/**
 * This class tests the Service class for Garage.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_service_unit_testing_setup_with_mockito
 */
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

    /**
     * Set up the DAO mock for garage
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the creation of a garage, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the creation of a garage, when the number is negative
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the creation of a garage, when the number already exists
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the creation of a garage, when the number is exceeds the maximum value of an integer
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the deletion of a garage, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the deletion of a garage, when the provided ID corresponds to no garage
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the modification of a garage, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyGarage() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.modifyGarage(garageNumberExisting1, garageNumberModification);
        } catch (Exception e) {
            error = e.getMessage();
            assertEquals("", error);
            fail();
        }
        assertNotNull(garage);
        assertEquals(garageNumberExisting1, garage.getGarageID());
        assertNotEquals(garageNumberExisting1, garage.getGarageNumber());
    }

    /**
     * Test the modification of a garage, when the provided ID corresponds to no a garage but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test the modification of a garage, when the garage number is zero
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyGarageNonPositiveGarageNumber() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.modifyGarage(garageNumberExisting1, garageNumberZero);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number must be positive.", error);
    }

    /**
     * Test the modification of a garage, when the provided number already exists
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyGarageExistentGarageNumber() {
        String error = null;
        Garage garage = null;
        try {
            garage = garageService.modifyGarage(garageNumberExisting1, garageNumberExisting2);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("This garage number already exists.", error);
    }

    /**
     * Test getting a garage, when the attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testGetGarage() {
        Garage garage = null;
        try {
            garage = garageService.getGarageService(garageNumberExisting2);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garage);
        assertEquals(garageNumberExisting2, garage.getGarageNumber());
    }

    /**
     * Test getting a garage when the provided ID corresponds to no garage
     *
     * @author Estefania Vazquez
     */
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

    /**
     * Test getting all garages (make sure that the number retrieved is the same as the number of garages added in the mock)
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testGetAllGarages() {
        List<Garage> garages = garageService.getAllGarageService();
        assertEquals(3, garages.size());
    }
}
