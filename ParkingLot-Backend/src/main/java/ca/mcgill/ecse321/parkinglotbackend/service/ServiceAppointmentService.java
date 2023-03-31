package ca.mcgill.ecse321.parkinglotbackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.ServiceAppointmentRepository;
import ca.mcgill.ecse321.parkinglotbackend.model.Car;
import ca.mcgill.ecse321.parkinglotbackend.model.Garage;
import ca.mcgill.ecse321.parkinglotbackend.model.OfferedService;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment.AppointmentStatus;

@Service
public class ServiceAppointmentService {

    @Autowired
    ServiceAppointmentRepository appointmentRepository;

    /**
     * Creates an appointment
     * @param garage Garage associated to appointment
     * @param service Service of the appointment
     * @param car Car for the appointment
     * @return Service appointment that is created
     * @author anniegouchee
     */
    @Transactional
    public ServiceAppointment createAppointment(Garage garage, OfferedService service, Car car){
        String error = "";

        //Checks if parameters are valid
        if (garage == null){
            error += "Garage cannot be empty";
        }

        if (service == null){
            error += "Service cannot be empty";
        }

        if (car == null){
            error += "Car cannot be empty";
        }

        error = error.trim();

        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }

        //Creates the service appointment
        ServiceAppointment appointment = new ServiceAppointment();
        appointment.setAppointmentStatus(AppointmentStatus.InProgress);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        //appointment.setStartTime(null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    /**
     * Gets the appointment given the ID
     * @param id ID of the appointment
     * @return Appointment given the ID 
     * @throws Exception No appointment exists with given ID
     * @author anniegouchee
     */
    @Transactional
    public ServiceAppointment findAppointmentByID(Long id) throws Exception{
        ServiceAppointment appointment = appointmentRepository.findAppointmentByServiceAppointmentID(id);
        if (appointment == null){
            throw new Exception("No appointment with this ID exists");
        }
        return appointment;
    }

    /**
     * Gets a list of service appointments for a car given its id
     * @param carID ID of the car 
     * @return List of appointments given the car's ID
     * @throws Exception No car exists for the ID
     * @author anniegouchee
     */
    @Transactional
    public  List<ServiceAppointment> getAppointmentsByCarID(Long carID) throws Exception{
        List<ServiceAppointment> appointments = appointmentRepository.findAppointmentByCar_CarID(carID);
        if (appointments == null || appointments.isEmpty()){
            throw new Exception("No car with this ID exists");
        }
        return appointments;
    }

    /**
     * Gets a list of the service appointments for a given service 
     * @param serviceID ID of the service
     * @return List of service appointments of the service with the given ID
     * @throws Exception No service exists for a given ID
     * @author anniegouchee
     */
    @Transactional
    public  List<ServiceAppointment> getAppointmentsByServiceID(Long serviceID) throws Exception{
        List<ServiceAppointment> appointments = appointmentRepository.findAppointmentByService_ServiceID(serviceID);
        if (appointments == null || appointments.isEmpty()){
            throw new Exception("No service with this ID exists");
        }
        return appointments;
    }

    /**
     * Get all the appointments
     * @return List of all appointments
     * @author anniegouchee
     */
    @Transactional
    public List<ServiceAppointment> getAllAppointments() {
        return toList(appointmentRepository.findAll());
    }

    /**
     * Deletes an appintment given its id
     * @param id ID of the appointment
     * @return The deleted appointment
     * @throws Exception No appointment exists for the ID
     * @author anniegouchee
     */
    @Transactional
    public ServiceAppointment deleteAppointment(Long id) throws Exception{
        ServiceAppointment appointment = appointmentRepository.findAppointmentByServiceAppointmentID(id);

        if (appointment == null){
            throw new Exception("No appointment with this ID exists");
        }

        appointmentRepository.delete(appointment);
        return appointment;
    }

    /**
     * Updates the appointment with given parameters
     * @param id ID of appoinmtnet
     * @param startTime Appointment start time
     * @param status Status of updated appointment
     * @param garage Garage for updated apppointment
     * @param service Service for updated appointment
     * @param car Car for updated appointment
     * @return Updated service appointment
     * @throws Exception No service appointment exists for given ID
     * @autho anniegouchee
     */
    @Transactional
    public ServiceAppointment updateAppointment(Long id, LocalDateTime startTime, AppointmentStatus status, Garage garage, OfferedService service, Car car) throws Exception{
        String error = "";

        ServiceAppointment appointment = findAppointmentByID(id);

        //Checks if parameters are valid
        if (appointment == null){
            error += "No appointment with this ID exists";
        }
        if (startTime == null){
            error += "Start time cannot be empty";
        }
        if (status == null){
            error += "Status cannot be empty";
        }
        if (garage == null){
            error += "Garage cannot be empty";
        }
        if (service == null ){
            error += "Service cannot be empty";
        }
        if (car == null){
            error += "Car cannot be empty";
        }

        error = error.trim();

        if (error.length() > 0){
            throw new IllegalArgumentException(error);
        }

        //Updates the service appointment
        appointment.setAppointmentStatus(status);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        appointment.setStartTime(startTime);
        appointmentRepository.save(appointment);

        return appointment;

    }

    /**
     * Helper method that converts to a list
     * @param <T>
     * @param iterable
     * @return List
     * @author anniegouchee
     */
    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
    }

}
