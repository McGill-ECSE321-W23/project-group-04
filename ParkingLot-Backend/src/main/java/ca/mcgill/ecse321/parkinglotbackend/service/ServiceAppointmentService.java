package ca.mcgill.ecse321.parkinglotbackend.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.dao.CarRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;
import ca.mcgill.ecse321.parkinglotbackend.dao.OfferedServiceRepository;
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
    @Autowired
    CarRepository carRepository;
    @Autowired
    GarageRepository garageRepository;
    @Autowired
    OfferedServiceRepository serviceRepository;

    @Transactional
    public ServiceAppointment createAppointment(Garage garage, OfferedService service, Car car){
        String error = "";

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

        ServiceAppointment appointment = new ServiceAppointment();
        appointment.setAppointmentStatus(AppointmentStatus.InProgress);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        //appointment.setStartTime(null);
        appointmentRepository.save(appointment);
        return appointment;
    }

    @Transactional
    public ServiceAppointment findAppointmentByID(Long id) throws Exception{
        ServiceAppointment appointment = appointmentRepository.findAppointmentByServiceAppointmentID(id);
        if (appointment == null){
            throw new Exception("No appointment with this ID exists");
        }
        return appointment;
    }

    @Transactional
    public  List<ServiceAppointment> getAppointmentsByCarID(Long carID) throws Exception{
        if (carRepository.findCarByCarID(carID) == null){
            throw new Exception("No car with this ID exists");
        }
        List<ServiceAppointment> appointments = appointmentRepository.findAppointmentByCar_CarID(carID);
        return appointments;
    }

    @Transactional
    public  List<ServiceAppointment> getAppointmentsByServiceID(Long serviceID) throws Exception{
        if (!serviceRepository.existsById(serviceID)){
            throw new Exception("No service with this ID exists");
        }
        List<ServiceAppointment> appointments = appointmentRepository.findAppointmentByService_ServiceID(serviceID);
        return appointments;
    }

    @Transactional
    public List<ServiceAppointment> getAllAppointments() {
        return toList(appointmentRepository.findAll());
    }

    @Transactional
    public ServiceAppointment deleteAppointment(Long id) throws Exception{
        ServiceAppointment appointment = appointmentRepository.findAppointmentByServiceAppointmentID(id);

        if (appointment == null){
            throw new Exception("No appointment with this id exists");
        }

        appointmentRepository.delete(appointment);
        return appointment;
    }

    @Transactional
    public ServiceAppointment updateAppointment(Long id, LocalDateTime startTime, AppointmentStatus status, Garage garage, OfferedService service, Car car) throws Exception{
        String error = "";

        ServiceAppointment appointment = findAppointmentByID(id);

        if (appointment == null){
            error += "No appointment with this ID exists";
        }
        if (startTime == null){
            error += "Start time cannot be empty";
        }
        if (status == null){
            error += "Status time cannot be empty";
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

        appointment.setAppointmentStatus(status);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        appointment.setStartTime(startTime);
        appointmentRepository.save(appointment);

        return appointment;

    }

    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
    }

}
