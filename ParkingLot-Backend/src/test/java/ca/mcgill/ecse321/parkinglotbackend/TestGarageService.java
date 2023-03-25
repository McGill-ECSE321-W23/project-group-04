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
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

@ExtendWith(MockitoExtension.class)
public class TestGarageService {
    @Mock
    private GarageRepository garageDao;

    @InjectMocks
    private GarageService garageService;

    private static final String GARAGE_KEY = "TestGarage";

    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> { return invocation.getArgument(0);};
        lenient().when(garageDao.save(any(Garage.class))).thenAnswer(returnParameterAsAnswer);          // Whenever anything is saved, just return the parameter object
    }

    // Tests

    @Test
    public void testCreateGarage() {
        int garageNumber = 1;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumber);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garage);
        checkResultCreateGarage(garage, garageNumber);
    }

    private void checkResultCreateGarage(Garage garage, int garageNumber) {
        assertEquals(garageNumber, garage.getGarageNumber());
    }

    @Test
    public void testCreateGarageNegativeGarageNumber() {
        String error = null;
        int garageNumber = -1;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumber);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number cannot be negative.", error);
    }

    @Test
    public void testCreateGarageZeroGarageNumber() {
        String error = null;
        int garageNumber = 0;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumber);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number cannot be zero.", error);
    }

    @Test
    public void testCreateGarageExistentGarageNumber() {
        String error = null;
        int garageNumber1 = 1;
        int garageNumber2 = 1;
        Garage garage2 = null;
        try {
            garageService.createGarageService(garageNumber1);
            garage2 = garageService.createGarageService(garageNumber2);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage2);
        assertEquals("This garage number already exists.", error);
    }

    @Test
    public void testCreateGarageExceedingIntLimitGarageNumber() {
        String error = null;
        int garageNumber = Integer.MAX_VALUE + 1;
        Garage garage = null;
        try {
            garage = garageService.createGarageService(garageNumber);   // Should get a negative number because of overflow
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage number cannot be negative.", error);
    }

    @Test
    public void testDeleteGarage() {
        int garageNumber = 1;
        Garage garageCreated = null;
        Garage garageDeleted = null;
        try {
            garageCreated = garageService.createGarageService(garageNumber);
            garageDeleted = garageService.deleteGarageService(garageCreated.getGarageID());
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garageDeleted);
        checkResultDeleteGarage(garageCreated, garageDeleted);
    }

    private void checkResultDeleteGarage(Garage garageCreated, Garage garageDeleted) {
        assertEquals(garageCreated.getGarageID(), garageDeleted.getGarageID());
        assertEquals(garageCreated.getGarageNumber(), garageDeleted.getGarageNumber());
        try {
            assertNull(garageService.getGarageService(garageDeleted.getGarageID()));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void testDeleteGarageEmptyGarageList() {
        String error = null;
        int garageID = 0;
        Garage garage = null;
        try {
            garage = garageService.deleteGarageService(garageID);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("No garages exist.", error);
    }

    @Test
    public void testDeleteNonExistentGarage() {
        String error = null;
        int garageNumber = 1;
        Garage garage = null;
        int garageID = 0;
        try {
            garageService.createGarageService(garageNumber);
            garage = garageService.deleteGarageService(garageID);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage not found.", error);
    }

    @Test
    public void testModifyGarage() {
        int garageNumberPrev = 1;
        int garageNumberPost = 2;
        Garage garageCreated = null;
        Garage garageModified = null;
        try {
            garageCreated = garageService.createGarageService(garageNumberPrev);
            garageModified = garageService.modifyGarage(garageCreated.getGarageID(), garageNumberPost);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(garageModified);
        checkResultModifyGarage(garageCreated, garageModified);
    }

    private void checkResultModifyGarage(Garage garageCreated, Garage garageModified) {
        assertEquals(garageCreated.getGarageID(), garageModified.getGarageID());
        assertNotEquals(garageCreated.getGarageNumber(), garageModified.getGarageNumber());
    }

    @Test
    public void testModifyGarageEmptyGarageList() {
        String error = null;
        int garageID = 0;
        int garageNumber = 1;
        Garage garage = null;
        try {
            garage = garageService.modifyGarage(garageID, garageNumber);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("No garages exist.", error);
    }

    @Test
    public void testModifyNonExistentGarage() {
        String error = null;
        int garageNumberPre = 1;
        int garageNumberPost = 2;
        Garage garage = null;
        int garageID = 0;
        try {
            garageService.createGarageService(garageNumberPre);
            garage = garageService.modifyGarage(garageID, garageNumberPost);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garage);
        assertEquals("Garage not found.", error);
    }

    @Test
    public void testModifyGarageNegativeGarageNumber() {
        String error = null;
        int garageNumberPrev = 1;
        int garageNumberPost = -1;
        Garage garageCreated = null;
        Garage garageModified = null;
        try {
            garageCreated = garageService.createGarageService(garageNumberPrev);
            garageModified = garageService.modifyGarage(garageCreated.getGarageID(), garageNumberPost);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("Garage number cannot be negative.", error);
    }

    @Test
    public void testModifyGarageZeroGarageNumber() {
        String error = null;
        int garageNumberPrev = 1;
        int garageNumberPost = 0;
        Garage garageCreated = null;
        Garage garageModified = null;
        try {
            garageCreated = garageService.createGarageService(garageNumberPrev);
            garageModified = garageService.modifyGarage(garageCreated.getGarageID(), garageNumberPost);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("Garage number cannot be zero.", error);
    }

    @Test
    public void testModifyGarageExceedingIntLimitGarageNumber() {
        String error = null;
        int garageNumberPrev = 1;
        int garageNumberPost = Integer.MAX_VALUE + 1;
        Garage garageCreated = null;
        Garage garageModified = null;
        try {
            garageCreated = garageService.createGarageService(garageNumberPrev);
            garageModified = garageService.modifyGarage(garageCreated.getGarageID(), garageNumberPost);   // Should get a negative number because of overflow
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("Garage number cannot be negative.", error);
    }

    @Test
    public void testModifyGarageExistentGarageNumber() {
        String error = null;
        int garageNumber1 = 1;
        int garageNumber2 = 1;
        Garage garageCreated = null;
        Garage garageModified = null;
        try {
            garageCreated = garageService.createGarageService(garageNumber1);
            garageModified = garageService.modifyGarage(garageCreated.getGarageID(), garageNumber2);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(garageModified);
        assertEquals("This garage number already exists.", error);
    }

    @Test
    public void testGetGarage() {

    }

    private void checkResultGetGarage(Garage garageCreated, Garage garageModified) {
        assertEquals(garageCreated.getGarageID(), garageModified.getGarageID());
        assertNotEquals(garageCreated.getGarageNumber(), garageModified.getGarageNumber());
    }

}
