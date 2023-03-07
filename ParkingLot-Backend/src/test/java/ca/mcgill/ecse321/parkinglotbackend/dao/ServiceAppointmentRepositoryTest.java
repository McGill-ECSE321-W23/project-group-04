package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.Service;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.time.Month;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class ServiceAppointmentRepositoryTest {

    @Autowired
    private ServiceRepository serviceRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private ServiceAppointmentRepository serviceAppointmentRepository;
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private GarageRepository garageRepository;

    // clear database after testing
    @AfterEach
    public void clearDatabase() {
        serviceAppointmentRepository.deleteAll();
        carRepository.deleteAll();
        serviceRepository.deleteAll();
        garageRepository.deleteAll();
        personRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadServiceAppointment() {

        // create a Person object and save to database

        String ownerName = "Annie Gouchee";
        String phoneNumber = "5149628668";
        Person owner = new Person();
        owner.setName(ownerName);
        owner.setPhoneNumber(phoneNumber);
        owner = personRepository.save(owner);
        Long ownerId = owner.getPersonID();

        // create a Car object and save to database

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

        // create a car wash object and save to database
        String serviceDescription = "Car Wash";
        int duration = 20;
        float cost = 15;
        Service carWash = new Service();
        carWash.setCost(cost);
        carWash.setDescription(serviceDescription);
        carWash.setDuration(duration);
        serviceRepository.save(carWash);
        Long serviceID = carWash.getServiceID();

        // create a Garage object and save to database
        Garage garage = new Garage();
        garage.setGarageNumber(2);
        garageRepository.save(garage);
        Long garageID = garage.getGarageID();

        // create a Service Appointment object and save to database
        LocalDateTime startTime = LocalDateTime.of(2022, Month.MARCH,4,5, 6, 7 );
        AppointmentStatus status = AppointmentStatus.Completed;
        ServiceAppointment appointment = new ServiceAppointment();
        appointment.setStartTime(startTime);
        appointment.setAppointmentStatus(status);
        appointment.setService(carWash);
        appointment.setCar(car);
        appointment.setGarage(garage);
        serviceAppointmentRepository.save(appointment);

        Long appointmentID = appointment.getServiceAppointmentID();

        car = null;
        carWash = null;
        appointment = null;
        garage = null;
        owner = null;

        // Retrieve the Service Appointment
        appointment = serviceAppointmentRepository.findAppointmentByServiceAppointmentID(appointmentID);

        // Assert that the retrieved information is correct
        assertNotNull(appointment);
        assertEquals(appointmentID, appointment.getServiceAppointmentID());
        assertNotNull(appointment.getCar());
        assertEquals(carId,appointment.getCar().getCarID());
        assertNotNull(appointment.getService());
        assertEquals(serviceID,appointment.getService().getServiceID());
    }

}
