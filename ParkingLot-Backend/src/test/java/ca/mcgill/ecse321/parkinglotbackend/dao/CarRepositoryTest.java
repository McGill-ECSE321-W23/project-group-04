package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class CarRepositoryTest {
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private CarRepository carRepository;

    @AfterEach
    public void clearDatabase() {
        // Delete the registrations first to avoid violating not-null constraint
        carRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCar() {

        String ownerName = "Annie Gouchee";
        String phoneNumber = "5149628668";
        Person owner = new Person();
        owner.setName(ownerName);
        owner.setPhoneNumber(phoneNumber);
        owner = personRepository.save(owner);
        Long ownerId = owner.getPersonID();

        String licensePlate = "E99VFK";
        String make = "Tesla";
        String model = "Model 3";

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        car.setOwner(owner);
        carRepository.save(car);
        Long carId = car.getCarID();

        car = null;
        owner = null;

        car = carRepository.findCarByCarID(carId);

        assertNotNull(car);
        assertEquals(carId, car.getCarID());

        assertNotNull(car.getOwner());
        assertEquals(ownerName,car.getOwner().getName() );

        assertNotNull(car.getLicensePlate());
        assertEquals(licensePlate,car.getLicensePlate() );

        /*
        car = carRepository.findCarByCarLicensePlate(licensePlate);
        assertNotNull(car);
        assertEquals(carId, car.getCarID());

        assertNotNull(car.getOwner());
        assertEquals(ownerName,car.getOwner().getName() );

        assertNotNull(car.getLicensePlate());
        assertEquals(licensePlate,car.getLicensePlate() );
        */

    }
}



