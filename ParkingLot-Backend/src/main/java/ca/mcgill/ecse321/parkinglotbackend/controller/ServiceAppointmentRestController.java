package ca.mcgill.ecse321.parkinglotbackend.controller;

import ca.mcgill.ecse321.parkinglotbackend.dto.*;
import ca.mcgill.ecse321.parkinglotbackend.dto.ServiceAppointmentDto.AppointmentStatusDto;
import ca.mcgill.ecse321.parkinglotbackend.model.*;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;
import ca.mcgill.ecse321.parkinglotbackend.service.CarService;
import ca.mcgill.ecse321.parkinglotbackend.service.GarageService;
import ca.mcgill.ecse321.parkinglotbackend.service.OfferedServiceService;
import ca.mcgill.ecse321.parkinglotbackend.service.ServiceAppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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

    /**
     * RESTful API that gets all service appointments
     * 
     * @return List of all service appointments
     * @author anniegouchee
     */
    @GetMapping(value = { "/get/all", "/get/all/" })
    public List<ServiceAppointmentDto> getAllAppointments() {
        return service.getAllAppointments().stream().map(a -> convertToDto(a)).collect(Collectors.toList());
    }

    /**
     * RESTful API that gets the appointment of a given ID
     * 
     * @return Appointment witht he given ID
     * @throws Exception No appointment exists with given ID
     * @author anniegouchee
     */
    @GetMapping(value = {"/get/{id}", "/get/{id}/"})
    public ServiceAppointmentDto getAppointmentById(@PathVariable("id") Long id) throws Exception{
        return convertToDto(service.findAppointmentByID(id));
    }

    /**
     * RESTful API that gets the list of appointments for a given car
     * @param id ID of the car
     * @return List of service appointments associated to the given car
     * @throws Exception No car associated with the license plate
     * @author anniegouchee
     */
    @GetMapping(value = {"/get/{id}", "/appointment/{id}/"})
    public List<ServiceAppointmentDto> getAppointmentByCarID(@PathVariable("id") Long id) throws Exception{
        return service.getAppointmentsByCarID(id).stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    /**
     * RESTful API that gets a list of aervice appointments for a given service
     * @param serviceID ID of the service
     * @return List of service appointments assiciated with the service
     * @throws Exception No service associated with the given servic ID
     * @author anniegouchee
     */
    @GetMapping(value = {"/get/byServiceID/{serviceID}", "/get/byServiceID/{serviceID}/"})
    public List<ServiceAppointmentDto> getAppointmentByServiceID(@PathVariable("serviceID") Long serviceID) throws Exception{
        return service.getAppointmentsByServiceID(serviceID).stream().map(c -> convertToDto(c)).collect(Collectors.toList());
    }

    /**
     * RESTful API that create a servuce appointment
     * @param garage Garage associated with service appointment
     * @param offeredService Service of the service appoointment
     * @param car Car of the service appointment
     * @return Service appointment with the given parameters
     * @author anniegouchee
     */
    @PostMapping(value = {"/create", "/create/"})
    public ServiceAppointmentDto createAppointment(GarageDto garage, OfferedServiceDto offeredService, CarDto car) {
        ServiceAppointment appointment = service.createAppointment(convertToDomainObject(garage), convertToDomainObject(offeredService),convertToDomainObject(car));
        return convertToDto(appointment);
    }
    
    /**
     * RESTful API for updating a service appointment
     * @param id ID of the service appointment to be updated
     * @param startTime Start time of the updated service appointment
     * @param status Status of the updated service appointment
     * @param garage Garage of the updated appointment
     * @param offeredService Service of the updates appointment
     * @param car Car of the updated appointment
     * @return Updated Service Appointment
     * @throws Exception No appointment associated with the ID
     * @author anniegouchee
     */
    @PutMapping(value = {"/update/{id}", "/update/{id}/"})
    public ServiceAppointmentDto updateAppointment(@PathVariable("id") Long id, LocalDateTime startTime, AppointmentStatus status, GarageDto garage, OfferedServiceDto offeredService, CarDto car) throws Exception{
        ServiceAppointment appointment = service.updateAppointment(id, startTime, status, convertToDomainObject(garage), convertToDomainObject(offeredService),convertToDomainObject(car));
        
        updateAppointment(id, startTime, status, garage, offeredService, car);
        return convertToDto(appointment);
    }

    /**
     * RESTful API that deletes a given service appointment
     * @param id ID of service appointment to be deleted
     * @return Service appointment that was deleted
     * @throws Exception No service appointment associated with ID
     * @author anniegouchee
     */
    @DeleteMapping(value = {"/delete/{id}", "/delete/{id}/"})
    public ServiceAppointmentDto deleteServiceAppointment(@PathVariable("id") Long id) throws Exception{
        ServiceAppointment appointment = service.deleteAppointment(id);
        return convertToDto(appointment);
    }

    /**
     * Helper method coverts service appointment to DTO
     * 
     * @param appointment Service appointmnet to be converted to Dto
     * @return Appointment DTO
     * @author anneigouchee
     */
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

        //Checks the appointment status and makes a DTO deoending on the status
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

    /**
     * Helper method that converts Garage to DTO
     * @param garage Garage to be converted to DTO
     * @return Garage DTO
     * @author anniegouchee
     */
    private GarageDto convertToDto(Garage garage) {
        if (garage == null) {
            throw new IllegalArgumentException("There is no such Garage!");
        }
        GarageDto garageDto = new GarageDto(garage.getGarageID(), garage.getGarageNumber());
        return garageDto;
    }

    /**
     * Helper method that converts Offered Service to DTO
     * @param service Service to be converted to DTO
     * @return Offered Service DTO
     * @author anniegouchee
     */
    private OfferedServiceDto convertToDto(OfferedService service) {
        if (service == null) {
            throw new IllegalArgumentException("There is no such Service!");
        }
        OfferedServiceDto serviceDto = new OfferedServiceDto(service.getServiceID(), service.getDescription(), service.getCost(), service.getDuration());
        return serviceDto;
    }

    /**
     * Helper method that converts Peron to DTO
     * @param p Person to be converted to DTO
     * @return Person DTO
     * @author anniegouchee
     */
    private PersonDto convertToDto(Person p){
        if (p == null) {
            throw new IllegalArgumentException("There is no such Person!");
        }
        PersonDto personDto = new PersonDto();
        return personDto;
    }

    /**
     * Helper method that converts Car to DTO
     * @param c Car to be converted
     * @return Car DTO
     * @author anniegouchee
     */
    private CarDto convertToDto(Car c) {
        if (c == null) {
            throw new IllegalArgumentException("There is no such Car!");
        }
        CarDto carDto = new CarDto(c.getCarID(), c.getLicensePlate(), c.getMake(), c.getModel(), convertToDto(c.getOwner()));
        return carDto;
    }

    /**
     * Helper method that converted Garage DTO to domain object
     * @param gDto Garage DTO to be converted
     * @return Garage domain object
     * @author anniegouchee
     */
    private Garage convertToDomainObject(GarageDto gDto) {
        List<Garage> garages = garageService.getAllGarageService();        
        for (Garage g : garages) {
            if (g.getGarageID() == gDto.getGarageID()){
                return g;
            }
        }
        return null;
    }

    /**
     * Helper method that converts Offered Service to domain object
     * @param sDto Offered service DTO to be converted
     * @return Offered Service domain object
     * @author anniegouchee
     */
    private OfferedService convertToDomainObject(OfferedServiceDto sDto) {
        List<OfferedService> services = offeredServiceService.getAllOfferedServiceService();
        
        for (OfferedService s : services) {
            if (s.getServiceID()== sDto.getOfferedServiceID()) {
                return s;
            }
        }
        return null;
    }

    /**
     * Helper method that converts Car to fomain object
     * @param cDto Car DTO to bo converted
     * @return Car domain object
     * @author anniegouchee
     */
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
