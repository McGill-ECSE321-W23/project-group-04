package ca.mcgill.ecse321.parkinglotbackend.service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.lenient;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.PersonRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;

/**
 * Testing Car service class
 * This class followed the template from the tutorials provided:
 * https://mcgill-ecse321-w23.github.io/#_service_unit_testing_setup_with_mockito
 * @author anniegouchee
 */
@ExtendWith(MockitoExtension.class)
public class TestCarService {
    
    @Mock
    private CarRepository carRepository;
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private CarService service;


    //Mock DB
    private static final Person P1 = new Person(1, "1111", "Person 1");
    private static final Person P2 = new Person(2, "2222", "Person 2");
    private static final Person P3 = new Person(3, "3333", "Person 3");
    
    private static final Car C1 = new Car (1, "A11 AAA", "M1", "Mo1", P1);
    private static final Car C2 = new Car (2, "B22 BBB", "M2", "Mo2", P2);
    private static final Car C3 = new Car (3, "C33 CCC", "M3", "Mo3", P3);

    // Test data
    private static final long CAR_ID = 4;
    private static final String CAR_LICENSE = "D44 DDD";
    private static final String CAR_MAKE = "M4";
    private static final String CAR_MODEL = "Mo4";
    private static final Person CAR_OWNER = new Person (4, "4444", "Person 4");

    @BeforeEach
    public void setMockOutput() {
        lenient().when(carRepository.findCarByCarID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(C1.getCarID())) {
                return C1;
            } else if (invocation.getArgument(0).equals(C2.getCarID())){
                return C2;
            } else if (invocation.getArgument(0).equals(C3.getCarID())){
                return C3;
            } else {
                return null;
            }
        });

        lenient().when(carRepository.findCarByLicensePlate(anyString())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(C1.getLicensePlate())) {
                return C1;
            } else if (invocation.getArgument(0).equals(C2.getLicensePlate())){
                return C2;
            } else if (invocation.getArgument(0).equals(C3.getLicensePlate())){
                return C3;
            } else {
                return null;
            }
        });   

        lenient().when(carRepository.findAll()).thenAnswer((InvocationOnMock invocation) -> {
            return Arrays.asList(C1, C2, C3);
        });

        lenient().when(carRepository.findCarByOwner_PersonID(anyLong())).thenAnswer( (InvocationOnMock invocation) -> {
            if(invocation.getArgument(0).equals(C1.getOwner().getPersonID())) {
                return Arrays.asList(C1);
            } else if (invocation.getArgument(0).equals(C2.getOwner().getPersonID())){
                return Arrays.asList(C2);
            } else if (invocation.getArgument(0).equals(C3.getOwner().getPersonID())){
                return Arrays.asList(C3);
            } else {
                return null;
            }
        });  


        lenient().when(personRepository.save(any(Person.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });   

        lenient().when(carRepository.save(any(Car.class))).thenAnswer((InvocationOnMock invocation) -> {
            return invocation.getArgument(0);
        });   
    }

    @AfterEach
    public void resetMockDB() {
        C1.setCarID(1);
        C1.setLicensePlate("A11 AAA");
        C1.setMake("M1");
        C1.setModel("Mo1");
        C1.setOwner(P1);

        C2.setCarID(2);
        C2.setLicensePlate("B22 BBB");
        C2.setMake("M2");
        C2.setModel("Mo2");
        C2.setOwner(P2);

        C3.setCarID(3);
        C3.setLicensePlate("C33 CCC");
        C3.setMake("M3");
        C3.setModel("Mo3");
        C3.setOwner(P3);
    }

    //Tests a successful car registration
    @Test
    public void testRegisterCar_Success() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, CAR_LICENSE, CAR_MAKE, CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(car);
        assertEquals(CAR_OWNER.getPersonID(), car.getOwner().getPersonID());
        assertEquals(CAR_LICENSE, car.getLicensePlate());
        assertEquals(CAR_MAKE, car.getMake());
        assertEquals(CAR_MODEL, car.getModel());
       // assertEquals(CAR_ID, car.getCarID());
    }

    //Tests car registration with no owner
    @Test
    public void testRegisterCar_NullOwner() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(null, CAR_LICENSE, CAR_MAKE, CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Owner cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registration with no license plate
    @Test
    public void testRegisterCar_NullLicensePlate() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, null, CAR_MAKE, CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("License plate cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registration with empty string license plate
    @Test
    public void testRegisterCar_EmptyLicensePlate() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, "", CAR_MAKE, CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("License plate cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registration for null make
    @Test
    public void testRegisterCar_NullMake() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, CAR_LICENSE, null, CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Make cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registration for make with empty string
    @Test
    public void testRegisterCar_EmptyMake() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, CAR_LICENSE, "", CAR_MODEL);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Make cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registraiton for null model
    @Test
    public void testRegisterCar_NullModel() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, CAR_LICENSE, CAR_MAKE, null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Model cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests car registration for model with empty string
    @Test
    public void testRegisterCar_EmptyModel() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.registerCar(CAR_OWNER, CAR_LICENSE, CAR_MAKE, "");
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Model cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests successfully getting car by ID 
    @Test
    public void testGetCarByCarID_Success() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.getCarByID(C1.getCarID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(car);
        assertEquals(C1, car);
        assertEquals(C1.getCarID(), car.getCarID());    
    
    }

    //Tests get car by ID with invalid ID
    @Test
    public void testGetCarByCarID_InvalidID() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.getCarByID(CAR_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No car with this ID exists", eMSG);
        assertNull(car);
    }

    // Tests successfully get car by license plate
    @Test
    public void testGetCarByLicensePlate_Success() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.getCarByLicensePlate(C1.getLicensePlate());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(car);
        assertEquals(C1, car);    
        assertEquals(C1.getLicensePlate(), car.getLicensePlate());    
    }

    //Tests get car by license plate with invalid license
    @Test
    public void testGetCarByLicensePlate_InvalidLicensePlate() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.getCarByLicensePlate(CAR_LICENSE);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No car with this license exists", eMSG);
        assertNull(car);
    }

    //Tests successful get car by owner
    @Test
    public void testGetCarByOwnerID_Success() {
        String eMSG = "";
        List<Car> car = null;

        try {
            car = service.findCarByOwnerID(C1.getOwner().getPersonID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("", eMSG);
        assertNotNull(car);
        assertNotNull(car.get(0));
        assertEquals(C1, car.get(0));
        assertEquals(C1.getOwner().getPersonID(), car.get(0).getOwner().getPersonID());    
    }

    //Tests get car by owener with invalid ID
    @Test
    public void testGetCarByOwnerID_InvalidID() {
        String eMSG = "";
        List<Car> car = null;

        try {
            car = service.findCarByOwnerID(CAR_OWNER.getPersonID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No person with this ID exists", eMSG);
        assertNull(car);
    }


    //Tests get car by ID with null ID
    @Test
    public void testGetCarByOwnerID_NullID() {
        String eMSG = "";
        List<Car> car = null;

        try {
            car = service.findCarByOwnerID(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }
        
        assertEquals("No person with this ID exists", eMSG);
        assertNull(car);
    }

    //Test get all cars
    @Test
    public void testGetAllCars() {
        List<Car> cars = service.getAllCars();
        assertEquals(3, cars.size());
        assertEquals(C1, cars.get(0));
        assertEquals(C2, cars.get(1));
        assertEquals(C3, cars.get(2));
    }

    //Tests successfully deleting car
    @Test
    public void testDeleteCar_Success() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.deleteCar(C1.getCarID());
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(car);
        assertEquals(C1, car);
    }

    //Tests deleting car with invalid ID
    @Test
    public void testDeleteCar_InvalidID() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.deleteCar(CAR_ID);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No car with this ID exists", eMSG);
        assertNull(car);
    }

    //Tests delete car with null ID
    @Test
    public void testDeleteCar_NullID() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.deleteCar(null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No car with this ID exists", eMSG);
        assertNull(car);
    }

    //Tests update car successfully
    @Test
    public void testUpdateCar_Success() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, CAR_MAKE, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("", eMSG);
        assertNotNull(car);
        assertEquals(C1.getCarID(), car.getCarID());
        assertEquals(CAR_LICENSE, car.getLicensePlate());
        assertEquals(CAR_MAKE, car.getMake());
        assertEquals(CAR_MODEL, car.getModel());
        assertEquals(CAR_OWNER, car.getOwner());
    }

    //Tests update Car with incalid ID
    @Test
    public void testUpdateCar_InvalidID() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(CAR_ID, CAR_LICENSE, CAR_MAKE, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No car with this ID exists", eMSG);
        assertNull(car);
    }

    //Tests update car with null ID
    @Test
    public void testUpdateCar_NullID() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(null, CAR_LICENSE, CAR_MAKE, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("No car with this ID exists", eMSG);
        assertNull(car);
    }

    //Tests update car with null license
    @Test
    public void testUpdateCar_NullLicense() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), null, CAR_MAKE, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("License plate cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests update car with empty license
    @Test
    public void testUpdateCar_EmptyLicense() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), "", CAR_MAKE, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("License plate cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests update car with null make
    @Test
    public void testUpdateCar_NullMake() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, null, CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Make cannot be empty", eMSG);
        assertNull(car);
    }


    //Tests update car with invalid make
    @Test
    public void testUpdateCar_InvalidMake() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, "", CAR_MODEL, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Make cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests update car wuth empty model
    @Test
    public void testUpdateCar_EmptyModel() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, CAR_MAKE, "", CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Model cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests update car with null model
    @Test
    public void testUpdateCar_NullModel() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, CAR_MAKE, null, CAR_OWNER);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Model cannot be empty", eMSG);
        assertNull(car);
    }

    //Tests update car with null owner
    @Test
    public void testUpdateCar_NullPerson() {
        String eMSG = "";
        Car car = null;

        try {
            car = service.updateCar(C1.getCarID(), CAR_LICENSE, CAR_MAKE, CAR_MODEL, null);
        } catch (Exception e) {
            eMSG = e.getMessage();
        }

        assertEquals("Owner cannot be null", eMSG);
        assertNull(car);
    }
}
