package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import org.springframework.data.repository.CrudRepository;


public interface ServiceAppointmentRepository extends CrudRepository<ServiceAppointment, String>{

    ServiceAppointment findAppointmentByServiceAppointmentID(Long serviceAppointmentID);

    List<ServiceAppointment> findAppointmentByCarID(Long carID);
    List<ServiceAppointment> findAppointmentByServiceID(Long serviceID);

}