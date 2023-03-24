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
    ServiceAppointmentRepository appointmentRepositoy;
    @Autowired
    CarRepository carRepository;
    @Autowired
    GarageRepository garageRepository;
    @Autowired
    OfferedServiceRepository serviceRepository;

    @Transactional
    public ServiceAppointment createAppointment(Garage garage, OfferedService service, Car car){
        ServiceAppointment appointment = new ServiceAppointment();
        appointment.setAppointmentStatus(AppointmentStatus.InProgress);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        //appointment.setStartTime(null);
        appointmentRepositoy.save(appointment);
        return appointment;
    }

    @Transactional
    public ServiceAppointment findAppointmentByID(Long id) throws Exception{
        ServiceAppointment appointment = appointmentRepositoy.findAppointmentByServiceAppointmentID(id);
        if (appointment == null){
            throw new Exception("No appointment with this ID exists");
        }
        return appointment;
    }

    @Transactional
    public  List<ServiceAppointment> getAppointmentsByCar(Car car){
        List<ServiceAppointment> appointments = appointmentRepositoy.findAppointmentByCar(car);
        return appointments;
    }

    @Transactional
    public  List<ServiceAppointment> getAppointmentsByService(OfferedService s){
        List<ServiceAppointment> appointments = appointmentRepositoy.findAppointmentByService(s);
        return appointments;
    }

    @Transactional
    public List<ServiceAppointment> getAllAppointments() {
        return toList(appointmentRepositoy.findAll());
    }

    @Transactional
    public ServiceAppointment deleteAppointment(Long id){
        ServiceAppointment appointment = appointmentRepositoy.findAppointmentByServiceAppointmentID(id);
        appointmentRepositoy.delete(appointment);
        return appointment;
    }

    @Transactional
    public ServiceAppointment updateAppointment(Long id, LocalDateTime startTime, AppointmentStatus status, Garage garage, OfferedService service, Car car) throws Exception{
        ServiceAppointment appointment = findAppointmentByID(id);

        if (status == null){
            throw new Exception("An appointment requires a status.");
        }
        if (garage == null){
            throw new Exception("An appointment requires a garage.");
        }
        if (service == null){
            throw new Exception("An appointment needs a service type");
        }
        if (car == null){
            throw new Exception("An appointment needs a car");
        }

        appointment.setAppointmentStatus(status);
        appointment.setCar(car);
        appointment.setGarage(garage);
        appointment.setService(service);
        appointment.setStartTime(startTime);
        appointmentRepositoy.save(appointment);

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
