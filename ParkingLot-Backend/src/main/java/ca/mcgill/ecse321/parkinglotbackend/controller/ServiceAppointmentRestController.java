package ca.mcgill.ecse321.parkinglotbackend.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.mcgill.ecse321.parkinglotbackend.dto.CarDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.GarageDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.OfferedServiceDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.PersonDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.ServiceAppointmentDto;
import ca.mcgill.ecse321.parkinglotbackend.dto.ServiceAppointmentDto.AppointmentStatusDto;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.model.Person;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.GarageService;
import ca.mcgill.ecse321.parkinglotbackend.service.ServiceAppointmentService;
import net.bytebuddy.implementation.bytecode.constant.NullConstant;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/api/appointments")
public class ServiceAppointmentRestController {

    @Autowired
    ServiceAppointmentService service;
    @Autowired
    CarService carService;
    @Autowired
    OfferedServiceService offeredServiceService;
    @Autowired
    GarageService garageService;


    @GetMapping(value = { "/get/all", "/get/all/" })
    public List<ServiceAppointmentDto> getAllAppointments() {
        return service.getAllAppointments().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    @GetMapping(value = {"/get/{id}", "/get/{id}/"})
    public ServiceAppointmentDto getAppointmentById(@PathVariable("id") Long id) throws Exception{
        return convertToDto(service.findAppointmentByID(id));
    }

    @GetMapping(value = {"/get/{licensePlate}", "/appointment/{licensePlate}/"})
    public List<ServiceAppointmentDto> getAppointmentByCarID(@PathVariable("licensePlate") String licensePlate) throws Exception{
        Car car = carService.getCarByLicensePlate(licensePlate);
        return service.getAppointmentsByCarID(car.getCarID()).stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    @GetMapping(value = {"/get/byServiceID/{serviceID}", "/get/byServiceID/{serviceID}/"})
    public List<ServiceAppointmentDto> getAppointmentByServiceID(@PathVariable("serviceID") Long serviceID) throws Exception{
        OfferedService offeredService = offeredServiceService.getOfferedServiceService(serviceID);
        return service.getAppointmentsByServiceID(offeredService.getServiceID()).stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }


    @PostMapping(value = {"/create", "/create/"})
    public ServiceAppointmentDto createAppointment(GarageDto garage, OfferedServiceDto offeredService, CarDto car) {
        ServiceAppointment appointment = service.createAppointment(convertToDomainObject(garage), convertToDomainObject(offeredService),convertToDomainObject(car));
        return convertToDto(appointment);
    }
    

    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public ServiceAppointmentDto updateAppointment(@PathVariable("id") Long id, LocalDateTime startTime, AppointmentStatus status, GarageDto garage, OfferedServiceDto offeredService, CarDto car) throws Exception{
        ServiceAppointment appointment = service.updateAppointment(id, startTime, status, convertToDomainObject(garage), convertToDomainObject(offeredService),convertToDomainObject(car));
        
        updateAppointment(id, startTime, status, garage, offeredService, car);
        return convertToDto(appointment);
    }

    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ServiceAppointmentDto deleteServiceAppointment(@PathVariable("id") Long id) throws Exception{
        ServiceAppointment appointment = service.deleteAppointment(id);
        return convertToDto(appointment);
    }

    private ServiceAppointmentDto convertToDto(ServiceAppointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("There is no such Appointment!");
        }

        ServiceAppointmentDto appointmentDto = new ServiceAppointmentDto();
        appointmentDto.setServiceAppointmentID(appointment.getServiceAppointmentID());
        appointmentDto.setStartTime(appointment.getStartTime());
        appointmentDto.setService(convertToDto(appointment.getService()));
        appointmentDto.setGarage(convertToDto(appointment.getGarage()));
        appointmentDto.setCar(convertToDto(appointment.getCar()));

        switch(appointment.getAppointmentStatus()){
            case Ready:
                appointmentDto.setAppointmentStatus(AppointmentStatusDto.Ready);
                return appointmentDto;
            case Completed:
                appointmentDto.setAppointmentStatus(AppointmentStatusDto.Completed);
                return appointmentDto;
            case InProgress:
                appointmentDto.setAppointmentStatus(AppointmentStatusDto.InProgress);
                return appointmentDto;
        }
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

    private Garage convertToDomainObject(GarageDto gDto) {
        List<Garage> garages = garageService.getAllGarageService();        
        for (Garage g : garages) {
            if (g.getGarageID() == gDto.getGarageID()){
                return g;
            }
        }
        return null;
    }

    private OfferedService convertToDomainObject(OfferedServiceDto sDto) {
        List<OfferedService> services = offeredServiceService.getAllOfferedServiceService();
        
        for (OfferedService s : services) {
            if (s.getServiceID()== sDto.getOfferedServiceID()) {
                return s;
            }
        }
        return null;
    }

    private Car convertToDomainObject(CarDto cDto) {
        List<Car> cars = carService.getAllCars();
        
        for (Car c : cars) {
            if (c.getCarID()== cDto.getCarID()) {
                return c;
            }
        }
        return null;
    }



}
