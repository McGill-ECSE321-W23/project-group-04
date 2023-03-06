package ca.mcgill.ecse321.parkinglotbackend.dao;

import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Service;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;


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


    @AfterEach
    public void clearDatabase() {
        carRepository.deleteAll();
        serviceRepository.deleteAll();
        serviceAppointmentRepository.deleteAll();
    }

    @Test
    public void testPersistAndLoadCar() {
        String licensePlate = "E99VFK";
        String make = "Tesla";
        String model = "Model 3";

        Car car = new Car();
        car.setLicensePlate(licensePlate);
        car.setMake(make);
        car.setModel(model);
        carRepository.save(car);
        Long carId = car.getCarID();

        String serviceDescription = "Car Wash";
        int duration = 20;
        float cost = 15;
        Service carWash = new Service();
        carWash.setCost(cost);
        carWash.setDescription(serviceDescription);
        carWash.setDuration(duration);
        serviceRepository.save(carWash);
        String serviceID = carWash.getServiceID();

        LocalDateTime startTime = LocalDateTime.of(2022, Month.MARCH,4,5, 6, 7 );
        AppointmentStatus status = AppointmentStatus.Completed;
        ServiceAppointment appointment = new ServiceAppointment();
        appointment.setStartTime(startTime);
        appointment.setAppointmentStatus(status);
        appointment.setService(carWash);
        appointment.setCar(car);

        Long appointmentID = appointment.getServiceAppointmentID();

        car = null;
        carWash = null;
        appointment = null;

        appointment = serviceAppointmentRepository.findAppointmentByServiceAppointmentID(appointmentID);
        assertNotNull(appointment);
        assertEquals(appointmentID, appointment.getServiceAppointmentID());

        assertNotNull(appointment.getCar());
        assertEquals(carId,appointment.getCar().getCarID());

        assertNotNull(appointment.getService());
        assertEquals(serviceID,appointment.getService().getServiceID());
    }



}
