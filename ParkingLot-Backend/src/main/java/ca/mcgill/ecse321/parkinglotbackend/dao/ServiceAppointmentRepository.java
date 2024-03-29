package ca.mcgill.ecse321.parkinglotbackend.dao;

import java.util.List;

import ca.mcgill.ecse321.parkinglotbackend.model.ServiceAppointment;
import org.springframework.data.repository.CrudRepository;


public interface ServiceAppointmentRepository extends CrudRepository<ServiceAppointment, String>{

    boolean existsAppointmentByServiceAppointmentID(Long serviceAppointmentID);
    ServiceAppointment findAppointmentByServiceAppointmentID(Long serviceAppointmentID);

    boolean existsAppointmentByCar_CarID(Long carID);
    List<ServiceAppointment> findAppointmentByCar_CarID(Long carID);

    boolean existsAppointmentByService_ServiceID(Long serviceID);
    List<ServiceAppointment> findAppointmentByService_ServiceID(Long serviceID);

}