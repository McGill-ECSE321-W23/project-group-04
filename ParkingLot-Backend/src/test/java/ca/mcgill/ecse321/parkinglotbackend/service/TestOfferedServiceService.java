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

import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * This class tests the Service class for OfferedService.
 *
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_service_unit_testing_setup_with_mockito
 */
@ExtendWith(MockitoExtension.class)
public class TestOfferedServiceService {
    @Mock
    private OfferedServiceRepository offeredServiceDao;

    @InjectMocks
    private OfferedServiceService offeredServiceService;

    // Data inside database
    private static final long offeredServiceIDExistent1 = 1;
    private static final String offeredServiceDescriptionExistent1 = "Change tires";
    private static final float offeredServiceCostExistent1 = 1;
    private static final int offeredServiceDurationExistent1 = 1;

    private static final long offeredServiceIDExistent2 = 2;
    private static final String offeredServiceDescriptionExistent2 = "Clean car";
    private static final float offeredServiceCostExistent2 = 2;
    private static final int offeredServiceDurationExistent2 = 2;

    private static final long offeredServiceIDExistent3 = 3;
    private static final String offeredServiceDescriptionExistent3 = "Change oil";
    private static final float offeredServiceCostExistent3 = 3;
    private static final int offeredServiceDurationExistent3 = 3;

    private static final String offeredServiceDescriptionCreation = "Apply antioxidant coat";
    private static final float offeredServiceCostCreation = 4;
    private static final int offeredServiceDurationCreation = 4;

    private static final String offeredServiceDescriptionModification = "Change wipers";
    private static final float offeredServiceCostModification = 5;
    private static final int offeredServiceDurationModification = 5;

    private static final String offeredServiceDescriptionEmpty = "";
    private static final String offeredServiceDescriptionSpace = "   ";

    private static final float offeredServiceCostNegative = -1;

    private static final int offeredServiceDurationNegative = -1;
    private static final int offeredServiceDurationZero = 0;

    private static final long offeredServiceIDNonExistent = 5;

    /**
     * Set up the DAO mock for offered service
     *
     * @Author Estefania Vazquez
     */
    @BeforeEach
    public void setMockOutput() {
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        };

        lenient().when(offeredServiceDao.save(any(OfferedService.class))).thenAnswer(returnParameterAsAnswer);          // Whenever anything is saved, just return the parameter object

        lenient().when(offeredServiceDao.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            List<OfferedService> offeredServices = new ArrayList<OfferedService>();
            offeredServices.add(new OfferedService(offeredServiceIDExistent1, offeredServiceDescriptionExistent1, offeredServiceCostExistent1, offeredServiceDurationExistent1));
            offeredServices.add(new OfferedService(offeredServiceIDExistent2, offeredServiceDescriptionExistent2, offeredServiceCostExistent2, offeredServiceDurationExistent2));
            offeredServices.add(new OfferedService(offeredServiceIDExistent3, offeredServiceDescriptionExistent3, offeredServiceCostExistent3, offeredServiceDurationExistent3));
            return offeredServices;
        });

        lenient().when(offeredServiceDao.findOfferedServiceByServiceID(anyLong())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals((long) 1)) {
                return new OfferedService(offeredServiceIDExistent1, offeredServiceDescriptionExistent1, offeredServiceCostExistent1, offeredServiceDurationExistent1);
            }
            else if (invocation.getArgument(0).equals((long) 2)){
                return new OfferedService(offeredServiceIDExistent2, offeredServiceDescriptionExistent2, offeredServiceCostExistent2, offeredServiceDurationExistent2);
            }
            else if (invocation.getArgument(0).equals((long) 3)) {
                return new OfferedService(offeredServiceIDExistent3, offeredServiceDescriptionExistent3, offeredServiceCostExistent3, offeredServiceDurationExistent3);
            }
            else {
                return null;
            }
        });
    }

    /**
     * Test the creation of an offered service, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testCreateOfferedService() {
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.createOfferedServiceService(offeredServiceDescriptionCreation, offeredServiceCostCreation, offeredServiceDurationCreation);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(offeredService);
        assertEquals(offeredServiceDescriptionCreation, offeredService.getDescription());
        assertEquals(offeredServiceCostCreation, offeredService.getCost());
        assertEquals(offeredServiceDurationCreation, offeredService.getDuration());
    }

    /**
     * Test the creation of an offered service, when the description is empty
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testCreateOfferedServiceEmptyDescription() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.createOfferedServiceService(offeredServiceDescriptionEmpty, offeredServiceCostCreation, offeredServiceDurationCreation);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Description cannot be empty.", error);
    }

    /**
     * Test the creation of an offered service, when the description already exists
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testCreateOfferedServiceExistingDescription() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.createOfferedServiceService(offeredServiceDescriptionExistent1, offeredServiceCostCreation, offeredServiceDurationCreation);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Description already exists.", error);
    }

    /**
     * Test the creation of an offered service, when the cost is negative
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testCreateOfferedServiceNegativeCost() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.createOfferedServiceService(offeredServiceDescriptionCreation, offeredServiceCostNegative, offeredServiceDurationCreation);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Cost cannot be negative.", error);
    }

    /**
     * Test the creation of an offered service, when the duration is negative
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testCreateOfferedServiceDurationNonPositive() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.createOfferedServiceService(offeredServiceDescriptionCreation, offeredServiceCostCreation, offeredServiceDurationNegative);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Duration must be positive.", error);
    }

    /**
     * Test the deletion of an offered service, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testDeleteOfferedService() {
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.deleteOfferedServiceService(offeredServiceIDExistent3);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(offeredService);
    }

    /**
     * Test the deletion of an offered service, when the provided ID corresponds to no offered service
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testDeleteNonExistentOfferedService() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.deleteOfferedServiceService(offeredServiceIDNonExistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Service not found.", error);
    }

    /**
     * Test the modification of an offered service, when all attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyOfferedService() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDExistent2, offeredServiceDescriptionModification, offeredServiceCostModification, offeredServiceDurationModification);
        } catch (Exception e) {
            error = e.getMessage();
            assertEquals("", error);
            fail();
        }
        assertNotNull(offeredService);
        assertEquals(offeredServiceIDExistent2, offeredService.getServiceID());
        assertNotEquals(offeredServiceDescriptionModification, offeredServiceDescriptionExistent2);
        assertNotEquals(offeredServiceCostModification, offeredServiceCostExistent2);
        assertNotEquals(offeredServiceDurationModification, offeredServiceDurationExistent2);
    }

    /**
     * Test the modification of an offered service, when the provided ID corresponds to no offered service but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyNonExistentOfferedService() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDNonExistent, offeredServiceDescriptionModification, offeredServiceCostModification, offeredServiceDurationModification);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Service not found.", error);
    }

    /**
     * Test the modification of an offered service, when the description only contains space but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyOfferedServiceSpaceDescription() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDExistent2, offeredServiceDescriptionSpace, offeredServiceCostModification, offeredServiceDurationModification);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Description cannot be empty.", error);
    }

    /**
     * Test the modification of an offered service, when the provided ID corresponds to no offered service but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyOfferedServiceExistingDescription() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDExistent2, offeredServiceDescriptionExistent3, offeredServiceCostModification, offeredServiceDurationModification);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Description already exists.", error);
    }

    /**
     * Test the modification of an offered service, when the cost is negative but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyOfferedServiceNegativeCost() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDExistent2, offeredServiceDescriptionModification, offeredServiceCostNegative, offeredServiceDurationModification);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Cost cannot be negative.", error);
    }

    /**
     * Test the modification of an offered service, when the duration is zero but all other attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testModifyOfferedServiceZeroDuration() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.modifyOfferedServiceService(offeredServiceIDExistent2, offeredServiceDescriptionModification, offeredServiceCostModification, offeredServiceDurationZero);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Duration must be positive.", error);
    }

    /**
     * Test getting an offered service, when the attributes respect our design decisions
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testGetOfferedService() {
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.getOfferedServiceService(offeredServiceIDExistent1);
        } catch (Exception e) {
            fail();
        }
        assertNotNull(offeredService);
        assertEquals(offeredServiceIDExistent1, offeredService.getServiceID());
    }

    /**
     * Test getting an offered service when the provided ID corresponds to no offered service
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testGetOfferedServiceNonExistent() {
        String error = null;
        OfferedService offeredService = null;
        try {
            offeredService = offeredServiceService.getOfferedServiceService(offeredServiceIDNonExistent);
        } catch (Exception e) {
            error = e.getMessage();
        }
        assertNull(offeredService);
        assertEquals("Service not found.", error);
    }

    /**
     * Test getting all offered services (make sure that the number retrieved is the same as the number of offered services added in the mock)
     *
     * @author Estefania Vazquez
     */
    @Test
    public void testGetAllOfferedService() {
        List<OfferedService> offeredServices = offeredServiceService.getAllOfferedServiceService();
        assertEquals(3, offeredServices.size());
    }
}
