package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.dto.CarDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.ServiceAppointmentDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.ServiceAppointmentService;

@CrossOrigin(origins = "*")
@RestController
public class ServiceAppointmentRestController {

    @Autowired
    ServiceAppointmentService service;
    @Autowired
    CarService carService;
    @Autowired
    OfferedServiceService 


    @GetMapping(value = { "/appointments", "/appointments/" })
    public List<ServiceAppointmentDto> getAllAppointments() {
        return service.getAllAppointments().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    @GetMapping(value = {"/appointment/{id}", "/appointment/{id}/"})
    public ServiceAppointmentDto getAppointmentById(@PathVariable("id") Long id) throws Exception{
        return convertToDto(service.findAppointmentByID(id));
    }

    @GetMapping(value = {"/appointment/{licensePlate}", "/appointment/{licensePlate}/"})
    public List<ServiceAppointmentDto> getAppointmentByCar(@PathVariable("licensePlate") String licensePlate) throws Exception{
        Car car = carService.getCarByLicensePlate(licensePlate);
        return service.getAppointmentsByCar(car).stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    @GetMapping(value = {"/appointment/{serviceID}", "/appointment/{serviceID}/"})
    public List<ServiceAppointmentDto> getAppointmentByCar(@PathVariable("service") Long serviceID){
        Service offeredService = OfferedServiceService.getOfferedService(serviceID);
        return service.getAppointmentsByService(offeredService).stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    @PutMapping("/update/{id}, /update/{id}/")
    public ServiceAppointmentDto updateAppointment(@PathVariable("id") Long id, LocalDateTime startTime, AppointmentStatus status, GarageDto garage, OfferedServiceDto offeredService, CarDto car) throws Exception{
        ServiceAppointment appointment = service.updateAppointment(id, startTime, status, garage, offeredService, car);
        return convertToDto(appointment);
        // not sure if im doing this right for enum class
    }

    @DeleteMapping("/delete/{id}, /delete/{id}/")
    public CarDto deleteCar(@PathVariable("id") Long id){
        ServiceAppointment appointment = service.deleteAppointment(id);
        return convertToDto(appointment);
    }

    private ServiceAppointmentDto convertToDto(ServiceAppointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("There is no such Appointment!");
        }
        ServiceAppointmentDto appointmentDto = new ServiceAppointmentDto(appointment.getServiceAppointmentID(), appointment.getStartTime(), appointment.getAppointmentStatus(),
            convertToDto(appointment.getService()), convertToDto(appointment.getGarage()), convertToDto(appointment.getCar()));
        return appointmentDto;
    }

    private GarageDto convertToDto(Garage garage) {
        if (garage == null) {
            throw new IllegalArgumentException("There is no such Garage!");
        }
        GarageDto garageDto = new GarageDto(garage.getGarageID(), garage.getGarageNumber());
        return garageDto;
    }

    private OfferedServiceDto convertToDto(OfferedService service) {
        if (service == null) {
            throw new IllegalArgumentException("There is no such Service!");
        }
        OfferedServiceDto serviceDto = new OfferedServiceDto(service.getServiceID(), service.getDescription(), service.getCost(), service.getDuration());
        return serviceDto;
    }

    private PersonDto convertToDto(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDto personDto = new PersonDto();
        return personDto;
    }

    private CarDto convertToDto(Car c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Car!");
        }
        CarDto carDto = new CarDto(c.getCarID(), c.getLicensePlate(), c.getMake(), c.getModel(), convertToDto(c.getOwner()));
        return carDto;
    }


}
